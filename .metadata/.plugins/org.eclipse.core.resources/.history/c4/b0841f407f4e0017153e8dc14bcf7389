package s6.suiviRegime.service;

import javax.xml.ws.Endpoint;

public class WebServiceStarter {
	public static void main(String args[]) {
		Endpoint.publish("http://localhost:2014/ConseilAlimentation", new WebAlimentationConseilService());
		System.out.println("Started");
	}
}
