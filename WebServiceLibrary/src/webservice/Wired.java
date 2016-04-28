
package webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for wired complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="wired">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="broadcastAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="currTranferRate" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="currUsedBandwidth" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="defaultGateway" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="interfaceName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ipAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="macAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mask" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="maxTransferRate" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="netAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="packetErrorRate" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wired", propOrder = {
    "broadcastAddress",
    "currTranferRate",
    "currUsedBandwidth",
    "defaultGateway",
    "interfaceName",
    "ipAddress",
    "macAddress",
    "mask",
    "maxTransferRate",
    "netAddress",
    "packetErrorRate"
})
@XmlSeeAlso({
    Wireless.class
})
public class Wired {

    protected String broadcastAddress;
    protected double currTranferRate;
    protected double currUsedBandwidth;
    protected String defaultGateway;
    protected String interfaceName;
    protected String ipAddress;
    protected String macAddress;
    protected String mask;
    protected int maxTransferRate;
    protected String netAddress;
    protected double packetErrorRate;

    /**
     * Gets the value of the broadcastAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBroadcastAddress() {
        return broadcastAddress;
    }

    /**
     * Sets the value of the broadcastAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBroadcastAddress(String value) {
        this.broadcastAddress = value;
    }

    /**
     * Gets the value of the currTranferRate property.
     * 
     */
    public double getCurrTranferRate() {
        return currTranferRate;
    }

    /**
     * Sets the value of the currTranferRate property.
     * 
     */
    public void setCurrTranferRate(double value) {
        this.currTranferRate = value;
    }

    /**
     * Gets the value of the currUsedBandwidth property.
     * 
     */
    public double getCurrUsedBandwidth() {
        return currUsedBandwidth;
    }

    /**
     * Sets the value of the currUsedBandwidth property.
     * 
     */
    public void setCurrUsedBandwidth(double value) {
        this.currUsedBandwidth = value;
    }

    /**
     * Gets the value of the defaultGateway property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDefaultGateway() {
        return defaultGateway;
    }

    /**
     * Sets the value of the defaultGateway property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDefaultGateway(String value) {
        this.defaultGateway = value;
    }

    /**
     * Gets the value of the interfaceName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInterfaceName() {
        return interfaceName;
    }

    /**
     * Sets the value of the interfaceName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInterfaceName(String value) {
        this.interfaceName = value;
    }

    /**
     * Gets the value of the ipAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * Sets the value of the ipAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIpAddress(String value) {
        this.ipAddress = value;
    }

    /**
     * Gets the value of the macAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMacAddress() {
        return macAddress;
    }

    /**
     * Sets the value of the macAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMacAddress(String value) {
        this.macAddress = value;
    }

    /**
     * Gets the value of the mask property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMask() {
        return mask;
    }

    /**
     * Sets the value of the mask property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMask(String value) {
        this.mask = value;
    }

    /**
     * Gets the value of the maxTransferRate property.
     * 
     */
    public int getMaxTransferRate() {
        return maxTransferRate;
    }

    /**
     * Sets the value of the maxTransferRate property.
     * 
     */
    public void setMaxTransferRate(int value) {
        this.maxTransferRate = value;
    }

    /**
     * Gets the value of the netAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNetAddress() {
        return netAddress;
    }

    /**
     * Sets the value of the netAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNetAddress(String value) {
        this.netAddress = value;
    }

    /**
     * Gets the value of the packetErrorRate property.
     * 
     */
    public double getPacketErrorRate() {
        return packetErrorRate;
    }

    /**
     * Sets the value of the packetErrorRate property.
     * 
     */
    public void setPacketErrorRate(double value) {
        this.packetErrorRate = value;
    }

}
