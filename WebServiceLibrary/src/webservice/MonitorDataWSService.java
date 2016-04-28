
package webservice;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "monitorDataWSService", targetNamespace = "http://WebService/", wsdlLocation = "http://192.168.1.69:8080/WebService/monitorData?WSDL")
public class MonitorDataWSService
    extends Service
{

    private final static URL MONITORDATAWSSERVICE_WSDL_LOCATION;
    private final static WebServiceException MONITORDATAWSSERVICE_EXCEPTION;
    private final static QName MONITORDATAWSSERVICE_QNAME = new QName("http://WebService/", "monitorDataWSService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://192.168.1.69:8080/WebService/monitorData?WSDL");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        MONITORDATAWSSERVICE_WSDL_LOCATION = url;
        MONITORDATAWSSERVICE_EXCEPTION = e;
    }

    public MonitorDataWSService() {
        super(__getWsdlLocation(), MONITORDATAWSSERVICE_QNAME);
    }

    public MonitorDataWSService(WebServiceFeature... features) {
        super(__getWsdlLocation(), MONITORDATAWSSERVICE_QNAME, features);
    }

    public MonitorDataWSService(URL wsdlLocation) {
        super(wsdlLocation, MONITORDATAWSSERVICE_QNAME);
    }

    public MonitorDataWSService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, MONITORDATAWSSERVICE_QNAME, features);
    }

    public MonitorDataWSService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public MonitorDataWSService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns MonitorDataWS
     */
    @WebEndpoint(name = "monitorDataWSPort")
    public MonitorDataWS getMonitorDataWSPort() {
        return super.getPort(new QName("http://WebService/", "monitorDataWSPort"), MonitorDataWS.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns MonitorDataWS
     */
    @WebEndpoint(name = "monitorDataWSPort")
    public MonitorDataWS getMonitorDataWSPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://WebService/", "monitorDataWSPort"), MonitorDataWS.class, features);
    }

    private static URL __getWsdlLocation() {
        if (MONITORDATAWSSERVICE_EXCEPTION!= null) {
            throw MONITORDATAWSSERVICE_EXCEPTION;
        }
        return MONITORDATAWSSERVICE_WSDL_LOCATION;
    }

}