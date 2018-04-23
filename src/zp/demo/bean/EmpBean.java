package zp.demo.bean;

public class EmpBean {
    private String id = "183208100";
    private String title = "业务总监";
    private String city = "济南";
    private String district = "济南";
    private String companyName = "山东华凌电缆有限公司";
    private String oriSalary = "面议";
    private String urlPath = "http://jn.hbrc.com/Company/detailcompInfo-68230-280092.html";
    private String startdate = "1488643200000";
    private String enddate = "1491235200000";
    private String source = "博才网";
    private String education = "大专";
    private String employerNumber = "";
    private String experience = "3-5年;5-10年,10年以上";
    private String createTime = "1488754470000";
    private String updateTime = "1488872931000";
    private String description = "工作职责br参与公司战略研究根据公司经营战略目标，提供公司年度经营预算，制订业务工作方针政策，提供公司内部业务管理改进方案并贯彻实施组织市场调研，收集有关市场信息，分析内外环境，确定目标市场，收集分析竞争对象信息，制订公司竞争策略并组织实施组织产品的定位及开发维持开拓业务渠道，不断扩大市场份额维护与关键客户的联系，参与重大业务洽谈，解决业务拓展中的重大问题，以此提升公司品牌形象。brbr职位职责br1完成公司年度业务目标以及其他任务br2有独立的业务渠道，具有良好的市场拓展能力br3负责业务部内部的部分管理及建设br4培训市场调查与新市场机会的发现br5新项目市场推广方案的制定br6成熟项目的业务组织协调和业务绩效管理br7业务员队伍的建设与培养等。";
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getDistrict() {
        return district;
    }
    
    public void setDistrict(String district) {
        this.district = district;
    }
    
    public String getCompanyName() {
        return companyName;
    }
    
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
    public String getOriSalary() {
        return oriSalary;
    }
    
    public void setOriSalary(String oriSalary) {
        this.oriSalary = oriSalary;
    }
    
    public String getUrlPath() {
        return urlPath;
    }
    
    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }
    
    public String getStartdate() {
        return startdate;
    }
    
    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }
    
    public String getEnddate() {
        return enddate;
    }
    
    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }
    
    public String getSource() {
        return source;
    }
    
    public void setSource(String source) {
        this.source = source;
    }
    
    public String getEducation() {
        return education;
    }
    
    public void setEducation(String education) {
        this.education = education;
    }
    
    public String getEmployerNumber() {
        return employerNumber;
    }
    
    public void setEmployerNumber(String employerNumber) {
        this.employerNumber = employerNumber;
    }
    
    public String getExperience() {
        return experience;
    }
    
    public void setExperience(String experience) {
        this.experience = experience;
    }
    
    public String getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    
    public String getUpdateTime() {
        return updateTime;
    }
    
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String toString() {
        StringBuffer sb = new StringBuffer();
        // "id": 180552199,
        sb.append(this.getId());
        // "title": "业务总监",
        sb.append(this.getTitle());
        // "title": "货运司机",
        sb.append(this.getCity());
        // "district": "济南",
        sb.append(this.getDistrict());
        // "companyName": "山东华凌电缆有限公司",
        sb.append(this.getCompanyName());
        // "oriSalary": "面议",
        sb.append(this.getOriSalary());
        // "urlPath": "http://jn.hbrc.com/Company/detailcompInfo-68230-280095.html",
        sb.append(this.getUrlPath());
        // "startdate": 1487433600000,
        sb.append(this.getStartdate());
        // "enddate": 1490025600000,
        sb.append(this.getSource());
        // "source": "博才网",
        sb.append(this.getEducation());
        // "education": "不限",
        sb.append(this.getEmployerNumber());
        // "employerNumber": "",
        sb.append(this.getExperience());
        // "experience": "['3-5年','5-10年','10年以上']",
        sb.append(this.getCreateTime());
        // "createTime": 1487564369000,
        sb.append(this.getUpdateTime());
        // "updateTime": 1487564369000,
        sb.append(this.getDescription());
        // "description": "录用后第一个为实习期，工资每月1500元管吃管住，实习完毕后每月2300元另加奖金。"
        sb.append(this.getDescription());
        return sb.toString();
    }
    
}
