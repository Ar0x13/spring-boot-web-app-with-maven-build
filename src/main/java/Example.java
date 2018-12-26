/**
 *  * Hello world!
 *  * Your current IP address:
 *  * Hostname:
 *  */

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
@EnableAutoConfiguration
public class Example {
	@RequestMapping("/")
	ResponseDTO home() {
		ResponseDTO dto = new ResponseDTO();
	        InetAddress ip;
        	String hostname;
		
		try {
            		System.out.println( "Hello World!" );

			ip = InetAddress.getLocalHost();
	        	System.out.println("Your current IP address : " + ip);
		        hostname = ip.getHostName();

   		        System.out.println("Hostname: " +  hostname);
        	        dto.setIp(ip.getHostAddress());
	                dto.setHostName(ip.getHostName());

        	}
	        catch (UnknownHostException ex) {
        	    ex.printStackTrace();
	        }

	return dto;
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Example.class, args);
	}
        
	public class ResponseDTO { 
		private String ip;
		private String hostName;
		public String getIp () {
			return ip;
		}
		public void setIp (String ip){
			this.ip = ip;
		}
                public String getHostName () {
                        return hostName;
                }
                public void setHostName (String hostName){
                        this.hostName = hostName;
                }
	}
}

