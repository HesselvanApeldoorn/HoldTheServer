package server;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.HighScoreDao;

@WebService
public class Hello {
    private String message = new String("Hello, ");

    public Hello(String message) {
    	this.message = message;
    }

	public void Hellos(String message) {
    	this.message =message;}

    @WebMethod
    @Path("/a")
	@GET
	@Produces(MediaType.TEXT_HTML)
    public String sayHello() {
        return message + ".";
    }
    
	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public static String getCount() {
		return "6";
	}
}