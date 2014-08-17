/*
 * Created on 29 avr. 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package fr.montagnesdor.restaurant.model.hibernate;


/**
 * @author Mathieu MA sous conrad
 *
 *	Cette classe est un mapping de la table t_user_address.
 */
public class UserAddress
{
	private Long id;
	
	private String address1;  
	private String address2;  
	private String address3;  
	private String postcode;
	private String city;  
	private String state;  
	// Maybe use ISO code with 2 as defined in JAVA Locale.class 
	private String country;
	private String digitalCode;
	private String staircase;
	private String stage;
	private String building;

	private User user;
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the address1
	 */
	public String getAddress1() {
		return address1;
	}
	/**
	 * @param address1 the address1 to set
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	/**
	 * @return the address2
	 */
	public String getAddress2() {
		return address2;
	}
	/**
	 * @param address2 the address2 to set
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	/**
	 * @return the address3
	 */
	public String getAddress3() {
		return address3;
	}
	/**
	 * @param address3 the address3 to set
	 */
	public void setAddress3(String address3) {
		this.address3 = address3;
	}
	/**
	 * @return the postcode
	 */
	public String getPostcode() {
		return postcode;
	}
	/**
	 * @param postcode the postcode to set
	 */
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return the digital Code
	 */
	public String getDigitalCode() {
		return digitalCode;
	}
	/**
	 * @param digital the digital Code to set
	 */
	public void setDigitalCode(String digitalCode) {
		this.digitalCode = digitalCode;
	}
	/**
	 * @return the staircase
	 */
	public String getStaircase() {
		return staircase;
	}
	/**
	 * @param staircase the staircase to set
	 */
	public void setStaircase(String staircase) {
		this.staircase = staircase;
	}
	/**
	 * @return the stage
	 */
	public String getStage() {
		return stage;
	}
	/**
	 * @param stage the stage to set
	 */
	public void setStage(String stage) {
		this.stage = stage;
	}
	/**
	 * @return the building
	 */
	public String getBuilding() {
		return building;
	}
	/**
	 * @param building the building to set
	 */
	public void setBuilding(String building) {
		this.building = building;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "UserAddress [id=" + id + ", address1=" + address1
				+ ", address2=" + address2 + ", address3=" + address3
				+ ", postcode=" + postcode + ", city=" + city + ", state="
				+ state + ", country=" + country + ", digitalCode=" + digitalCode
				+ ", staircase=" + staircase + ", stage=" + stage
				+ ", building=" + building + "]";
	}
}
