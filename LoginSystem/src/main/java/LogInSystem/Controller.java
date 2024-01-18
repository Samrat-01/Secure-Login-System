package LogInSystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    private userService uS;
    
    @CrossOrigin
    @PostMapping("/signup")
    public String signup(@RequestBody userData u) {
    	
    	if(uS.verificationData(u)=="0") {
    		
    		uS.signup(u);
    		return "User signed up successfully!";
    		
    	}else if(uS.verificationData(u)=="1") {
    		return "UserName Must Start From Alphabets,and it can include number , and it length should be 8 digit";
    	}else if(uS.verificationData(u)=="2") {
    		return "Name must be contain only Alphabets";
    	}else if(uS.verificationData(u)=="3") {
    		return "Password Should be 8 digits";
    	}
    	
    	return "User Already Exits!!";
    }
    @CrossOrigin
    @PostMapping
    ("/login/{userId}/{password}")
    public String login(@PathVariable String userId, @PathVariable String password) {
    	if (uS.login(userId, password)) {
    		return "Login successful!";
    	}
        return "Invalid ID or password";
    }
    @CrossOrigin
    @GetMapping("/resetIn")
    public String resetIn(@RequestBody userData u) {
    	if (uS.resetIn(u)) {
    		return "Password reset for user: " + u.getUserId();
    	}
    	return "User not found!";
    }
    @CrossOrigin
    @PutMapping("/updatePassword/{userId}/{newPassword}")
    public String updatePassword(@PathVariable String userId,@PathVariable String newPassword) {
    	if(uS.updatePassword(userId,newPassword)) {
    		return "Password Updated";
    	}
    	return "Password Must Be 8 Digit!";
    }
    
    @CrossOrigin
    @GetMapping("/checkUserExists/{userId}")
    public boolean checkUserExists(@PathVariable String userId) {
        return uS.userExistsByUserId(userId);
    }
}
