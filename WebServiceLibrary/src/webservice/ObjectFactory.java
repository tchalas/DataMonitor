
package webservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the webservice package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SetMonitorData_QNAME = new QName("http://WebService/", "setMonitorData");
    private final static QName _SetMonitorDataResponse_QNAME = new QName("http://WebService/", "setMonitorDataResponse");
    private final static QName _SetTerminalData_QNAME = new QName("http://WebService/", "setTerminalData");
    private final static QName _SetTerminalDataResponse_QNAME = new QName("http://WebService/", "setTerminalDataResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: webservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SetMonitorData }
     * 
     */
    public SetMonitorData createSetMonitorData() {
        return new SetMonitorData();
    }

    /**
     * Create an instance of {@link SetTerminalData }
     * 
     */
    public SetTerminalData createSetTerminalData() {
        return new SetTerminalData();
    }

    /**
     * Create an instance of {@link SetMonitorDataResponse }
     * 
     */
    public SetMonitorDataResponse createSetMonitorDataResponse() {
        return new SetMonitorDataResponse();
    }

    /**
     * Create an instance of {@link SetTerminalDataResponse }
     * 
     */
    public SetTerminalDataResponse createSetTerminalDataResponse() {
        return new SetTerminalDataResponse();
    }

    /**
     * Create an instance of {@link Wireless }
     * 
     */
    public Wireless createWireless() {
        return new Wireless();
    }

    /**
     * Create an instance of {@link AccessPoint }
     * 
     */
    public AccessPoint createAccessPoint() {
        return new AccessPoint();
    }

    /**
     * Create an instance of {@link MonitorData }
     * 
     */
    public MonitorData createMonitorData() {
        return new MonitorData();
    }

    /**
     * Create an instance of {@link Wired }
     * 
     */
    public Wired createWired() {
        return new Wired();
    }

    /**
     * Create an instance of {@link TerminalData }
     * 
     */
    public TerminalData createTerminalData() {
        return new TerminalData();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetMonitorData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WebService/", name = "setMonitorData")
    public JAXBElement<SetMonitorData> createSetMonitorData(SetMonitorData value) {
        return new JAXBElement<SetMonitorData>(_SetMonitorData_QNAME, SetMonitorData.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetMonitorDataResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WebService/", name = "setMonitorDataResponse")
    public JAXBElement<SetMonitorDataResponse> createSetMonitorDataResponse(SetMonitorDataResponse value) {
        return new JAXBElement<SetMonitorDataResponse>(_SetMonitorDataResponse_QNAME, SetMonitorDataResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetTerminalData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WebService/", name = "setTerminalData")
    public JAXBElement<SetTerminalData> createSetTerminalData(SetTerminalData value) {
        return new JAXBElement<SetTerminalData>(_SetTerminalData_QNAME, SetTerminalData.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetTerminalDataResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://WebService/", name = "setTerminalDataResponse")
    public JAXBElement<SetTerminalDataResponse> createSetTerminalDataResponse(SetTerminalDataResponse value) {
        return new JAXBElement<SetTerminalDataResponse>(_SetTerminalDataResponse_QNAME, SetTerminalDataResponse.class, null, value);
    }

}
