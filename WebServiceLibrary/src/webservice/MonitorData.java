
package webservice;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for monitorData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="monitorData">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="wiredVector" type="{http://WebService/}wired" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="wirelessVector" type="{http://WebService/}wireless" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="apVector" type="{http://WebService/}accessPoint" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "monitorData", propOrder = {
    "wiredVector",
    "wirelessVector",
    "apVector"
})
public class MonitorData {

    @XmlElement(nillable = true)
    protected List<Wired> wiredVector;
    @XmlElement(nillable = true)
    protected List<Wireless> wirelessVector;
    @XmlElement(nillable = true)
    protected List<AccessPoint> apVector;

    /**
     * Gets the value of the wiredVector property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wiredVector property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWiredVector().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Wired }
     * 
     * 
     */
    public List<Wired> getWiredVector() {
        if (wiredVector == null) {
            wiredVector = new ArrayList<Wired>();
        }
        return this.wiredVector;
    }

    /**
     * Gets the value of the wirelessVector property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wirelessVector property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWirelessVector().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Wireless }
     * 
     * 
     */
    public List<Wireless> getWirelessVector() {
        if (wirelessVector == null) {
            wirelessVector = new ArrayList<Wireless>();
        }
        return this.wirelessVector;
    }

    /**
     * Gets the value of the apVector property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the apVector property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getApVector().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AccessPoint }
     * 
     * 
     */
    public List<AccessPoint> getApVector() {
        if (apVector == null) {
            apVector = new ArrayList<AccessPoint>();
        }
        return this.apVector;
    }

}
