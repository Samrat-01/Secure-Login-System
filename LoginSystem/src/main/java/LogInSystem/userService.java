package LogInSystem;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class userService {

    @Autowired
    private userRepository uR;
    
    public String verificationData(userData u) {
    	String userId = u.getUserId();
    	if(userExistsByUserId(userId)!=true) {
    		
    		 String userIDpattern = "^[A-Za-z][A-Za-z0-9]{7}$";
    		 Pattern regex = Pattern.compile(userIDpattern);
             java.util.regex.Matcher matcher = regex.matcher(u.getUserId());
             
             if(matcher.matches()) {
            	 
            	 String Namepattern = "^[A-Za-z]+( [A-Za-z]+)*$"; // for name
                 Pattern NameRegex = Pattern.compile(Namepattern);
                 java.util.regex.Matcher NameMatcher = NameRegex.matcher(u.getName());
                 
            	 if(NameMatcher.matches()) {
            		 String Passwordpattern = "^[\\p{ASCII}]{8}$"; // for password
                     Pattern PasswordRegex = Pattern.compile(Passwordpattern);
                     java.util.regex.Matcher PasswordMatcher = PasswordRegex.matcher(u.getPassword());
                     if(PasswordMatcher.matches()) {
                    	 return "0";
                     }else {
                    	 return "3";//issue with password
                     }
            		 
            	 }else {
            		 return"2";//issue with name 
            	 }
            	 
             }else {
            	 return "1";//issue with useID 
             }
             
             

             
    	}
    	return "111";//user already exits
    }
    
    public void signup(userData u) {
        uR.save(u);
    }

    public boolean login(String userId, String password) {
        return uR.existsByUserIdAndPassword(userId, password);
    }

    public boolean resetIn(userData u) {
        return uR.existsByUserIdAndDob(u.getUserId(), u.getDob());
    }

    public boolean updatePassword(String userId, String newPassword) {
        userData user = uR.findByUserId(userId);
        
        String Passwordpattern = "^[\\p{ASCII}]{8}$"; // for password
        Pattern PasswordRegex = Pattern.compile(Passwordpattern);
        java.util.regex.Matcher PasswordMatcher = PasswordRegex.matcher(newPassword);
        if(PasswordMatcher.matches()) {
        	if (user != null) {
                user.setPassword(newPassword);
                uR.save(user);
                return true;
            }
        }
        
        return false;
    }
    public boolean userExistsByUserId(String userId) {
        return uR.existsByUserId(userId);
    }
}
