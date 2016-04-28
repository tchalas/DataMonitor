
package webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for wireless complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="wireless">
 *   &lt;complexContent>
 *     &lt;extension base="{http://WebService/}wired">
 *       &lt;sequence>
 *         &lt;element name="accessPointStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="channel" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="connectionQuality" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descartedPackages" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="essid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="noise" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="receivedSignalStrenght" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="transmittedSignal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="WMac" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wireless", propOrder = {
    "accessPointStatus",
    "channel",
    "connectionQuality",
    "descartedPackages",
    "essid",
    "noise",
    "receivedSignalStrenght",
    "transmittedSignal",
    "wMac"
})
public class Wireless
    extends Wired
{

    protected String accessPointStatus;
    protected int channel;
    protected String connectionQuality;
    protected int descartedPackages;
    protected String essid;
    protected int noise;
    protected int receivedSignalStrenght;
    protected String transmittedSignal;
    @XmlElement(name = "WMac")
    protected String wMac;

    /**
     * Gets the value of the accessPointStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccessPointStatus() {
        return accessPointStatus;
    }

    /**
     * Sets the value of the accessPointStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccessPointStatus(String value) {
        this.accessPointStatus = value;
    }

    /**
     * Gets the value of the channel property.
     * 
     */
    public int getChannel() {
        return channel;
    }

    /**
     * Sets the value of the channel property.
     * 
     */
    public void setChannel(int value) {
        this.channel = value;
    }

    /**
     * Gets the value of the connectionQuality property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConnectionQuality() {
        return connectionQuality;
    }

    /**
     * Sets the value of the connectionQuality property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConnectionQuality(String value) {
        this.connectionQuality = value;
    }

    /**
     * Gets the value of the descartedPackages property.
     * 
     */
    public int getDescartedPackages() {
        return descartedPackages;
    }

    /**
     * Sets the value of the descartedPackages property.
     * 
     */
    public void setDescartedPackages(int value) {
        this.descartedPackages = value;
    }

    /**
     * Gets the value of the essid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEssid() {
        return essid;
    }

    /**
     * Sets the value of the essid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEssid(String value) {
        this.essid = value;
    }

    /**
     * Gets the value of the noise property.
     * 
     */
    public int getNoise() {
        return noise;
    }

    /**
     * Sets the value of the noise property.
     * 
     */
    public void setNoise(int value) {
        this.noise = value;
    }

    /**
     * Gets the value of the receivedSignalStrenght property.
     * 
     */
    public int getReceivedSignalStrenght() {
        return receivedSignalStrenght;
    }

    /**
     * Sets the value of the receivedSignalStrenght property.
     * 
     */
    public void setReceivedSignalStrenght(int value) {
        this.receivedSignalStrenght = value;
    }

    /**
     * Gets the value of the transmittedSignal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransmittedSignal() {
        return transmittedSignal;
    }

    /**
     * Sets the value of the transmittedSignal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransmittedSignal(String value) {
        this.transmittedSignal = value;
    }

    /**
     * Gets the value of the wMac property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWMac() {
        return wMac;
    }

    /**
     * Sets the value of the wMac property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWMac(String value) {
        this.wMac = value;
    }

}
