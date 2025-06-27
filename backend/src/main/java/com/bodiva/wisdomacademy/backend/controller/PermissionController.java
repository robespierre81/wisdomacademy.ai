import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api")
public class PermissionController {

    @GetMapping("/check-permission")
    public ResponseEntity<String> checkPermission(@RequestParam String address) {
        // Replace with your actual permission check logic
        boolean hasPermission = checkUserPermission(address);

        if (hasPermission) {
            return ResponseEntity.ok("Permission granted");
        } else {
            return ResponseEntity.status(403).body("Permission denied");
        }
    }

    private boolean checkUserPermission(String address) {
        // Mock logic for demo purposes
        // Replace with actual business logic, e.g., database check
        String validAddress = "0x5ae47b3d0206fa42386189cf33bc21E3938A0386";
        return validAddress.equals(address);
    }
}
