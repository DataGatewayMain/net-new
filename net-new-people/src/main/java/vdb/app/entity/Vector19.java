package vdb.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
@Entity
@Table(name = "vector19")
@Access(AccessType.FIELD)

public class Vector19 implements Serializable {

    private static final long serialVersionUID = 1L;
	
	

	public Vector19() {
		
	}
	
	@Id
	@Column(name = "id")
	private String id;
	
	
    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "pid",length = 20)
    private String pid;
	public Vector19(String id, String pid, String campaign_ID, String first_Name, String last_name, String email_address,
			String company_name, String company_domain, String job_title, String job_function, String job_level,
			String company_Address, String city, String state, String zip_Code, String country, String telephone_Number,
			String employee_size, String industry, String revenue, String sIC, String nAIC, String company_Link,
			String prospect_Link, String email_validation, String region, String api) {
		super();
		this.pid = pid;
		Campaign_ID = campaign_ID;
		First_Name = first_Name;
		this.last_name = last_name;
		this.email_address = email_address;
		this.company_name = company_name;
		this.company_domain = company_domain;
		this.job_title = job_title;
		this.job_function = job_function;
		this.job_level = job_level;
		Company_Address = company_Address;
		this.city = city;
		state = state;
		Zip_Code = zip_Code;
		this.country = country;
		Telephone_Number = telephone_Number;
		this.employee_size = employee_size;
		Industry = industry;
		Revenue = revenue;
		SIC = sIC;
		NAIC = nAIC;
		Company_Link = company_Link;
		Prospect_Link = prospect_Link;
		this.email_validation = email_validation;
		this.region = region;
		this.api = api;
		this.id = id;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getCampaign_ID() {
		return Campaign_ID;
	}
	public void setCampaign_ID(String campaign_ID) {
		Campaign_ID = campaign_ID;
	}
	public String getFirst_Name() {
		return First_Name;
	}
	public void setFirst_Name(String first_Name) {
		First_Name = first_Name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail_address() {
		return email_address;
	}
	public void setEmail_address(String email_address) {
		this.email_address = email_address;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getCompany_domain() {
		return company_domain;
	}
	public void setCompany_domain(String company_domain) {
		this.company_domain = company_domain;
	}
	public String getJob_title() {
		return job_title;
	}
	public void setJob_title(String job_title) {
		this.job_title = job_title;
	}
	public String getJob_function() {
		return job_function;
	}
	public void setJob_function(String job_function) {
		this.job_function = job_function;
	}
	public String getJob_level() {
		return job_level;
	}
	public void setJob_level(String job_level) {
		this.job_level = job_level;
	}
	public String getCompany_Address() {
		return Company_Address;
	}
	public void setCompany_Address(String company_Address) {
		Company_Address = company_Address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		state = state;
	}
	public String getZip_Code() {
		return Zip_Code;
	}
	public void setZip_Code(String zip_Code) {
		Zip_Code = zip_Code;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getTelephone_Number() {
		return Telephone_Number;
	}
	public void setTelephone_Number(String telephone_Number) {
		Telephone_Number = telephone_Number;
	}
	public String getEmployee_size() {
		return employee_size;
	}
	public void setEmployee_size(String employee_size) {
		this.employee_size = employee_size;
	}
	public String getIndustry() {
		return Industry;
	}
	public void setIndustry(String industry) {
		Industry = industry;
	}
	public String getRevenue() {
		return Revenue;
	}
	public void setRevenue(String revenue) {
		Revenue = revenue;
	}
	public String getSIC() {
		return SIC;
	}
	public void setSIC(String sIC) {
		SIC = sIC;
	}
	public String getNAIC() {
		return NAIC;
	}
	public void setNAIC(String nAIC) {
		NAIC = nAIC;
	}
	public String getCompany_Link() {
		return Company_Link;
	}
	public void setCompany_Link(String company_Link) {
		Company_Link = company_Link;
	}
	public String getProspect_Link() {
		return Prospect_Link;
	}
	public void setProspect_Link(String prospect_Link) {
		Prospect_Link = prospect_Link;
	}
	public String getEmail_validation() {
		return email_validation;
	}
	public void setEmail_validation(String email_validation) {
		this.email_validation = email_validation;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getApi() {
		return api;
	}
	public void setApi(String api) {
		this.api = api;
	}



	@Column(name = "campaign_id",length = 255 )
	private String Campaign_ID;
	@Column(name = "first_name" ,length = 255 )
	private String First_Name;
	  @Column(name = "last_name",length = 255 )
	private String last_name;
	@Column(name = "email_address",length = 255 )
	private String email_address;
	@Column(name = "company_name",length = 255 )
	private String company_name;
	@Column(name = "company_domain",length = 255 )
	private String company_domain;
	@Column(name = "job_title",length = 255 )
	private String job_title;
	@Column(name = "job_function",length = 255 )
	private String job_function;
	@Column(name = "job_level",length = 255 )
	private String job_level;
	@Column(name = "Company_Address")
	private String Company_Address;
	 @Column(name = "city",length = 255 )
	private String city;
	@Column(name = "state",length = 255 )
	private String state;
	@Column(name = "zip_code",length = 255 )
	private String Zip_Code;
	@Column(name = "country",length = 255 )
	private String country;
	@Column(name = "telephone_number",length = 255 )
	private String Telephone_Number;
	 @Column(name = "employee_size",length = 255 )
	private String employee_size;
	@Column(name = "Industry")
	private String Industry;
	@Column(name = "Revenue" )
	private String Revenue;
	@Column(name = "SIC")
	private String SIC;
	@Column(name = "NAIC" )
	private String NAIC;
	 @Column(name = "Company_Link",length = 1255 )
	private String Company_Link;
	@Column(name = "Prospect_Link",length = 1255 )
	private String Prospect_Link;
	@Column(name = "email_validation",length = 255 )
	private String email_validation;
	@Column(name = "region",length = 255 )
	private String region;
	@Column(name = "api",length = 255 )
	private String api;
	
	
}
