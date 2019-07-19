package com.youyicn.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "userNum")
    private String userNum;

    @Column(name = "hospitalId")
    private String hospitalId;

    @Column(name = "loginName")
    private String loginName;

    @Column(name = "real_name")
    private String realName;

    @Column(name = "user_pwd")
    private String userPwd;

    /**
     * 1：老师 2：学生 3：助理老师
     */
    @Column(name = "identity_id")
    private Integer identityId;

    private String sex;

    @Column(name = "qualification_id")
    private Integer qualificationId;

    private String dept;

    private Integer nation;

    @Column(name = "card_no")
    private String cardNo;

    @Column(name = "birth_time")
    private Date birthTime;

    private Integer education;

    private String image;

    @Column(name = "is_admin")
    private Byte isAdmin;

    @Column(name = "user_type_id")
    private Integer userTypeId;

    @Column(name = "is_login")
    private Byte isLogin;

    @Column(name = "login_ip")
    private String loginIp;

    @Column(name = "department_id")
    private Integer departmentId;

    @Column(name = "usb_key")
    private Byte usbKey;

    @Column(name = "module_manager")
    private String moduleManager;

    private String status;

    @Column(name = "baseName")
    private String baseName;

    @Column(name = "roomName")
    private String roomName;

    private String address;

    @Column(name = "gradSchool")
    private String gradschool;

    private String major;

    private String degree;

    private String xuewei;

    @Column(name = "certificationNum")
    private String certificationnum;

    @Column(name = "cellPhone")
    private String cellphone;

    private String staff;

    @Column(name = "trainTime")
    private Byte traintime;

    private String email;

    private Integer grade;

    /**
     * 是否已经安排轮转
     */
    @Column(name = "isAt")
    private Integer isat;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	public String getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	/**
     * @return real_name
     */
    public String getRealName() {
        return realName;
    }

    /**
     * @param realName
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * @return user_pwd
     */
    public String getUserPwd() {
        return userPwd;
    }

    /**
     * @param userPwd
     */
    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    /**
     * 获取1：老师 2：学生 3：助理老师
     *
     * @return identity_id - 1：老师 2：学生 3：助理老师
     */
    public Integer getIdentityId() {
        return identityId;
    }

    /**
     * 设置1：老师 2：学生 3：助理老师
     *
     * @param identityId 1：老师 2：学生 3：助理老师
     */
    public void setIdentityId(Integer identityId) {
        this.identityId = identityId;
    }

    /**
     * @return sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * @return qualification_id
     */
    public Integer getQualificationId() {
        return qualificationId;
    }

    /**
     * @param qualificationId
     */
    public void setQualificationId(Integer qualificationId) {
        this.qualificationId = qualificationId;
    }

    /**
     * @return dept
     */
    public String getDept() {
        return dept;
    }

    /**
     * @param dept
     */
    public void setDept(String dept) {
        this.dept = dept;
    }

    /**
     * @return nation
     */
    public Integer getNation() {
        return nation;
    }

    /**
     * @param nation
     */
    public void setNation(Integer nation) {
        this.nation = nation;
    }

    /**
     * @return card_no
     */
    public String getCardNo() {
        return cardNo;
    }

    /**
     * @param cardNo
     */
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    /**
     * @return birth_time
     */
    public Date getBirthTime() {
        return birthTime;
    }

    /**
     * @param birthTime
     */
    public void setBirthTime(Date birthTime) {
        this.birthTime = birthTime;
    }

    /**
     * @return education
     */
    public Integer getEducation() {
        return education;
    }

    /**
     * @param education
     */
    public void setEducation(Integer education) {
        this.education = education;
    }

    /**
     * @return image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return is_admin
     */
    public Byte getIsAdmin() {
        return isAdmin;
    }

    /**
     * @param isAdmin
     */
    public void setIsAdmin(Byte isAdmin) {
        this.isAdmin = isAdmin;
    }

    /**
     * @return user_type_id
     */
    public Integer getUserTypeId() {
        return userTypeId;
    }

    /**
     * @param userTypeId
     */
    public void setUserTypeId(Integer userTypeId) {
        this.userTypeId = userTypeId;
    }

    /**
     * @return is_login
     */
    public Byte getIsLogin() {
        return isLogin;
    }

    /**
     * @param isLogin
     */
    public void setIsLogin(Byte isLogin) {
        this.isLogin = isLogin;
    }

    /**
     * @return login_ip
     */
    public String getLoginIp() {
        return loginIp;
    }

    /**
     * @param loginIp
     */
    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    /**
     * @return department_id
     */
    public Integer getDepartmentId() {
        return departmentId;
    }

    /**
     * @param departmentId
     */
    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * @return usb_key
     */
    public Byte getUsbKey() {
        return usbKey;
    }

    /**
     * @param usbKey
     */
    public void setUsbKey(Byte usbKey) {
        this.usbKey = usbKey;
    }

    /**
     * @return module_manager
     */
    public String getModuleManager() {
        return moduleManager;
    }

    /**
     * @param moduleManager
     */
    public void setModuleManager(String moduleManager) {
        this.moduleManager = moduleManager;
    }

    /**
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }


    /**
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return gradSchool
     */
    public String getGradschool() {
        return gradschool;
    }

    /**
     * @param gradschool
     */
    public void setGradschool(String gradschool) {
        this.gradschool = gradschool;
    }
    
    
    public String getBaseName() {
		return baseName;
	}

	public void setBaseName(String baseName) {
		this.baseName = baseName;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	/**
     * @return major
     */
    public String getMajor() {
        return major;
    }

    /**
     * @param major
     */
    public void setMajor(String major) {
        this.major = major;
    }

    /**
     * @return degree
     */
    public String getDegree() {
        return degree;
    }

    /**
     * @param degree
     */
    public void setDegree(String degree) {
        this.degree = degree;
    }

    /**
     * @return xuewei
     */
    public String getXuewei() {
        return xuewei;
    }

    /**
     * @param xuewei
     */
    public void setXuewei(String xuewei) {
        this.xuewei = xuewei;
    }

    /**
     * @return certificationNum
     */
    public String getCertificationnum() {
        return certificationnum;
    }

    /**
     * @param certificationnum
     */
    public void setCertificationnum(String certificationnum) {
        this.certificationnum = certificationnum;
    }

    /**
     * @return cellPhone
     */
    public String getCellphone() {
        return cellphone;
    }

    /**
     * @param cellphone
     */
    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    /**
     * @return staff
     */
    public String getStaff() {
        return staff;
    }

    /**
     * @param staff
     */
    public void setStaff(String staff) {
        this.staff = staff;
    }

    /**
     * @return trainTime
     */
    public Byte getTraintime() {
        return traintime;
    }

    /**
     * @param traintime
     */
    public void setTraintime(Byte traintime) {
        this.traintime = traintime;
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return grade
     */
    public Integer getGrade() {
        return grade;
    }

    /**
     * @param grade
     */
    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    /**
     * 获取是否已经安排轮转
     *
     * @return isAt - 是否已经安排轮转
     */
    public Integer getIsat() {
        return isat;
    }

    /**
     * 设置是否已经安排轮转
     *
     * @param isat 是否已经安排轮转
     */
    public void setIsat(Integer isat) {
        this.isat = isat;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userNum='" + userNum + '\'' +
                ", hospitalId='" + hospitalId + '\'' +
                ", loginName='" + loginName + '\'' +
                ", realName='" + realName + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", identityId=" + identityId +
                ", sex='" + sex + '\'' +
                ", qualificationId=" + qualificationId +
                ", dept='" + dept + '\'' +
                ", nation=" + nation +
                ", cardNo='" + cardNo + '\'' +
                ", birthTime=" + birthTime +
                ", education=" + education +
                ", image='" + image + '\'' +
                ", isAdmin=" + isAdmin +
                ", userTypeId=" + userTypeId +
                ", isLogin=" + isLogin +
                ", loginIp='" + loginIp + '\'' +
                ", departmentId=" + departmentId +
                ", usbKey=" + usbKey +
                ", moduleManager='" + moduleManager + '\'' +
                ", status='" + status + '\'' +
                ", baseName='" + baseName + '\'' +
                ", roomName='" + roomName + '\'' +
                ", address='" + address + '\'' +
                ", gradschool='" + gradschool + '\'' +
                ", major='" + major + '\'' +
                ", degree='" + degree + '\'' +
                ", xuewei='" + xuewei + '\'' +
                ", certificationnum='" + certificationnum + '\'' +
                ", cellphone='" + cellphone + '\'' +
                ", staff='" + staff + '\'' +
                ", traintime=" + traintime +
                ", email='" + email + '\'' +
                ", grade=" + grade +
                ", isat=" + isat +
                '}';
    }
}