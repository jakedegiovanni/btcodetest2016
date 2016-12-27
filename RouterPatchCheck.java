import java.util.List;
import java.util.ArrayList;
import java.lang.Exception;

/**
 * @author Jake Degiovanni
 *
 * Main class of program, when invoked main will be called and the provided csv file will be
 * parsed and the routers in that file that need patching will be printed to the command line.
 * Any lines with invalid data will be notified to the user so they can be ammended.
 * See README for usage information.
 */
 public class RouterPatchCheck {

    private static List<Router> routers;  // global list of routers to be printed to command line
    
    public static void main(String [] args) {

        CSVParser csvParser = new CSVParser(args[0], ",");

        List<String[]> parsedCSV = csvParser.parse();

        routers = new ArrayList<>();

        if (parsedCSV != null) {
            for (String[] routerElements : parsedCSV) {
                if (routerElements.length > 0) {  // ignore blank lines
                    try {
                        double osVersion = Double.parseDouble(routerElements[3]);

                        checkPatchedData(routerElements[2]); // data validity
                        Boolean patched = routerElements[2].toLowerCase().equals("yes");

                        if (osVersion >= 12 && !patched) {
                            checkIP(routerElements[1]); // ensure data validity

                            Router router = new Router();
                            
                            router.setHostname(routerElements[0]);
                            router.setIpAddress(routerElements[1]);
                            router.setPatched(patched);
                            router.setOsVersion(osVersion);

                            String notes = routerElements.length == 5 ? routerElements[4] : null;
                            router.setNotes(notes);

                            if (checkRouter(router)) // if router valid then add to global list
                                routers.add(router);

                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Data for host " + routerElements[0]);
                        continue;
                    } catch (Exception e) {
                        System.out.println("Invalid Data for host " + routerElements[0]);
                        continue;
                    }
                }
            }
        }

        for (Router router : routers) {
            System.out.println(router.toString());
        }
    }

    /**
     * Check each value of the ip address to make sure that it is a valid format
     * i.e. all entries between dots are integers
     */
    private static void checkIP(String ip) throws NumberFormatException {
        String[] ipElements = ip.split("\\.", 4);

        for (String address : ipElements) {
            Integer.parseInt(address);
        }
    }

    /**
     * Check the value in the patched? column to ensure it is valid
     * i.e. value is either yes or no (after being converted to lowercase)
     */
    private static void checkPatchedData(String patched) throws Exception {
        String patchedString = patched.toLowerCase();
        boolean valid = (patchedString.equals("yes") || patchedString.equals("no"));
        
        if (!valid) {
            throw new Exception();
        }
    }

    /**
     * This method will check to see if the router we are attempting to add to our list is a valid router
     * i.e. we check to see if the hostname or the ip are already added
     * if they are we signal the router as invalid and remove the matching router due to its invalidity
     */
    private static boolean checkRouter(Router router) {
        if (routers.size() == 0)
            return true;

        for (int i = 0; i < routers.size(); i++) {
            if (routers.get(i).getHostname().toLowerCase().equals(router.getHostname().toLowerCase()) 
                    || routers.get(i).getIpAddress().equals(router.getIpAddress())) {
                
                // no need to decrement i to adjust pointer as we return out of this method directly after
                routers.remove(i); 
                return false;
            }
        }

        return true;
    }
 }