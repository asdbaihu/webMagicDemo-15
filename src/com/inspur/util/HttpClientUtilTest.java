package com.inspur.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HttpClientUtilTest {
    public static final Log logger = LogFactory.getLog(HttpClientUtilTest.class);
    
    public static void main(String[] args) {
        // testget1();
        testget2();
        // testpost1();
        
    }
    
    private static void testget2() {
        String url, ret;
        String comId = "233174024";
        String comName = URLEncoder.encode("山东华凌电缆有限公司");
        
        // 企业基本信息
        url = "http://www.tianyancha.com/v2/company/" + comId + ".json";
        // {"state":"ok","message":"","data":{"updatetime":1484147378747,"fromTime":4070880000000,"categoryScore":1954,"type":1,"id":233174024,"regNumber":"350581600073708","percentileScore":3701,"phoneNumber":"15377985793","regCapital":"","name":"山东华凌电缆有限公司","regInstitute":"福建省石狮市市场监督管理局","regLocation":"石狮市大仑市场45号","industry":"农业","approvedTime":1413302400000,"logo":"http://static.tianyancha.com/logo/ll/b9804374d222c760b2836845a18d6d62.png","businessScope":"蔬菜零售。 #（依法须经批准的项目，经相关部门批准后方可开展经营活动）","estiblishTime":920390400000,"regStatus":"存续（在营、开业、在册）","legalPersonName":"蔡良鑫","toTime":4070880000000,"legalPersonId":2156731349,"sourceFlag":"http://qyxy.baic.gov.cn/","actualCapital":"","flag":1,"correctCompanyId":"","companyOrgType":"家庭经营","updateTimes":1473134205000,"base":"fj","companyType":0,"companyId":29440655}}
        url = "http://www.tianyancha.com/wxApi/getJsSdkConfig.json?url=http%3A%2F%2Fwww.tianyancha.com%2Fcompany%2F" + comId + "";
        // {"timestamp":"1489641333","errno":"0","nonceStr":"ee31b7a2-a3af-4947-adb5-ce08c3fe19d6","jsapi_ticket":"sM4AOVdWfPE4DxkXGEs8VPaPsaDh6tb4mKK7lj32zgGxHKWp7xXpAs53DcGrMZLXYWMaVp6zDwq19N8P0r5c1w","signature":"630f6e0655065ffef569e4908cb6d493cd12329e","url":"http://www.tianyancha.com/company/233174024"}
        url = "http://dis.tianyancha.com/dis/exist/" + comId + ".json";
        // {"state":"ok","message":"","data":true}
        // 同地区同行业公司
        url = "http://www.tianyancha.com/v2/near/s.json?id=" + comId + "&pn=1";
        // {"state":"ok","message":"","data":{"total":5000,"items":[{"id":"1608018956","name":"山东寰宇线缆有限公司"},{"id":"1617081330","name":"济南浩天发电设备有限公司"},{"id":"1553389849","name":"山东华凌电缆科技有限公司"},{"id":"852869658","name":"济南富杰电器制造有限公司"},{"id":"2323440826","name":"济南力诺新能源有限公司"},{"id":"589375656","name":"章丘市众联线缆有限公司"},{"id":"2321240656","name":"济南金星线缆有限公司"},{"id":"327267766","name":""},{"id":"327272299","name":"济南希格玛电源科技有限责任公司"},{"id":"417915134","name":"济南承泽照明有限公司"}]}}
        // 天眼查数据更新时间
        url = "http://www.tianyancha.com/v2/updatetime/" + comId + ".json?random=1489632792499";
        // {"state":"ok","message":"","data":1488734626000}
        // // 新闻动态
        url = "http://www.tianyancha.com/v2/getnews/" + comName + ".json";
        // {"state":"ok","message":"","data":[{"title":"山东省助力地方创新驱动发展工程在济南启动","url":"http://www.bast.net.cn/art/2016/8/1/art_16651_315480.html","website":"首都科技网","time":"2016-08-01 06:20"},{"title":"山东华凌电缆有限公司","url":"http://www.jiancai365.cn/pinpai/hualingdianlan.html","website":"中国建材采购网","time":"2016-12-13 13:16"},{"title":"[视频]山东体制创新打破围墙 科技成果出深闺","url":"http://www.sd.xinhuanet.com/sdws/2016-04/02/c_1118519464.htm","website":"新华网山东频道","time":"2016-04-02 14:58"},{"title":"石油和化工行业采购服务平台首批电线电缆行业合格供应商评审结...","url":"http://www.cpcia.org.cn/html/3/20167/155808.html","website":"国家石油和化工网","time":"2016-07-04 09:39"},{"title":"山东首届“省长杯”工业设计大赛颁奖 10件产品获金奖","url":"http://news.dahe.cn/2016/10-13/107599567.html","website":"大河网新闻中心","time":"2016-10-13 09:28"},{"title":"电线电缆制造业转型升级求突破","url":"http://www.cnr.cn/tech/techgd/20160812/t20160812_522962676.shtml","website":"中国广播网","time":"2016-08-12 12:10"},{"title":"聊城公布82批次商品抽检信息 不合格30批次","url":"http://www.cqn.com.cn/ms/content/2016-07/26/content_3196473.htm","website":"中国质量新闻网","time":"2016-07-26 09:39"},{"title":"山东颁布省长质量奖管理办法 每两年一届设退出机制","url":"http://news.shm.com.cn/2016-06/13/content_4487400.htm","website":"烟台日报","time":"2016-06-13 06:07"},{"title":"山东首届“省长杯”工业设计大赛颁奖在潍坊举行","url":"http://www.wfcmw.cn/weifang/headline/2016-10-13/247402_3.html","website":"潍坊传媒网","time":"2016-10-13 14:04"},{"title":"全国工商联副主席安七一赴山东调研民营企业发展","url":"http://www.rmzxb.com.cn/c/2016-05-10/804212.shtml","website":"人民政协网","time":"2016-05-10 16:07"}]}
        // 主要人员2
        url = "http://www.tianyancha.com/expanse/staff.json?id=" + comId + "&ps=20&pn=1";
        // {"state":"ok","message":"","data":{"total":2,"result":[{"id":2066067222,"typeJoin":["监事"],"type":2,"name":"王兆波"},{"id":2020178303,"typeJoin":["执行董事兼总经理"],"type":2,"name":"梁志银"}]}}
        // 股东信息 3精确持股比例
        url = "http://www.tianyancha.com/expanse/holder.json?id=" + comId + "&ps=20&pn=1";
        // {"state":"ok","message":"","data":{"total":3,"result":[{"id":2020178303,"amount":6765.0,"capitalActl":[{"amomon":"0万元","paymet":"货币"}],"type":2,"capital":[{"amomon":"6,765.00万元","paymet":"货币","percent":"45.10%"}],"name":"梁志银"},{"id":2066067222,"amount":5925.0,"capitalActl":[{"amomon":"0万元","paymet":"货币"}],"type":2,"capital":[{"amomon":"5,925.00万元","paymet":"货币","percent":"39.50%"}],"name":"王兆波"},{"id":2065474443,"amount":2310.0,"capitalActl":[{"amomon":"0万元","paymet":"货币"}],"type":2,"capital":[{"amomon":"2,310.00万元","paymet":"货币","percent":"15.40%"}],"name":"王伟"}]}}
        // 对外投资 2 精确投资比例
        url = "http://www.tianyancha.com/expanse/inverst.json?id=" + comId + "&ps=20&pn=1";
        // {"state":"ok","message":"","data":{"total":2,"result":[{"id":1553389849,"amount":2600.0,"category":"电气机械和器材制造业","percent":"100%","regCapital":"2600万","name":"山东华凌电缆科技有限公司","base":"sd","regStatus":"在营","estiblishTime":1336665600000,"type":1,"legalPersonName":"王兆波","pencertileScore":8065},{"id":2392256855,"amount":150.0,"category":"橡胶和塑料制品业","percent":"6.67%","regCapital":"2250万","name":"山东科华赛邦新材料股份有限公司","base":"sd","regStatus":"在营","estiblishTime":1466524800000,"type":1,"legalPersonName":"王兆波","pencertileScore":7784}]}}
        // 变更记录4
        url = "http://www.tianyancha.com/expanse/changeinfo.json?id=" + comId + "&ps=5&pn=1";
        // {"state":"ok","message":"","data":{"total":4,"result":[{"changeItem":"经营范围变更","createTime":"2017-03-06","contentBefore":"普通货运(以上项目在审批机关批准的经营期限内经营)；电线电缆的生产、销售、安装；电气机械及器材、开关柜的批发、零售；通信系统工程施工；业务进出口。(未取得专项许可的项目除外)","contentAfter":"普通货运；电线电缆的生产、销售、安装、设计研发；电气机械及器材、开关柜的批发、零售；通信系统工程施工；业务进出口。（依法须经批准的项目，经相关部门批准后方可开展经营活动）","changeTime":"2015-06-01"},{"changeItem":"投资人(股权)变更","createTime":"2017-03-06","contentBefore":"王伟认缴1660实缴1660|王兆波认缴4175实缴4175|梁志银认缴4815实缴4815|单位:万元","contentAfter":"王伟认缴2310实缴0|王兆波认缴5925实缴0|梁志银认缴6765实缴0|单位:万元","changeTime":"2014-03-28"},{"changeItem":"注册资本(金)变更","createTime":"2017-03-06","contentBefore":"10650.0","contentAfter":"15000","changeTime":"2014-03-28"},{"changeItem":"经营范围变更","createTime":"2017-03-06","contentBefore":"许可经营项目：普通货运（有效期至：2014年3月23日）一般经营项目：电线电缆的生产、销售、安装；电气机械及器材、开关柜的批发、零售，通信系统工程施工。业务进出口（国家法律、法规规定禁止经营的除外，限制经营的取得许可后方可经营）。（国家有专项许可的除外）。(未取得专项许可的项目除外)","contentAfter":"普通货运(以上项目在审批机关批准的经营期限内经营)；电线电缆的生产、销售、安装；电气机械及器材、开关柜的批发、零售；通信系统工程施工；业务进出口。(未取得专项许可的项目除外)","changeTime":"2014-03-18"}]}}
        // 企业年报3
        url = "http://www.tianyancha.com/expanse/annu.json?id=" + comId + "&ps=5&pn=1"; // /reportContent/233174024/2015
        // {"state":"ok","message":"","data":[{"id":1124448117,"reportYear":"2015"},{"id":1124448111,"reportYear":"2014"},{"id":1124448107,"reportYear":"2013"}]}
        url = "http://dis.tianyancha.com/qq/" + comId + ".json?random=1489632794383";
        // {"state":"ok","message":"","data":{"name":"233174024","uv":706413,"pv":373518,"v":"33,102,117,110,99,116,105,111,110,40,110,41,123,100,111,99,117,109,101,110,116,46,99,111,111,107,105,101,61,39,114,116,111,107,101,110,61,97,101,49,57,101,97,53,100,55,48,97,50,52,54,98,57,56,52,57,52,102,50,56,54,56,57,100,102,53,54,100,100,59,112,97,116,104,61,47,59,39,59,110,46,119,116,102,61,102,117,110,99,116,105,111,110,40,41,123,114,101,116,117,114,110,39,51,44,49,52,44,51,52,44,51,48,44,49,51,44,50,56,44,51,49,44,49,52,44,49,44,49,44,49,56,44,51,50,44,50,57,44,49,56,44,52,44,49,57,44,51,50,44,50,55,44,52,44,48,44,49,56,44,48,44,51,52,44,49,44,50,56,44,49,56,44,51,48,44,50,57,44,50,55,44,51,52,44,50,56,44,49,57,39,125,125,40,119,105,110,100,111,119,41,59"}}
        // 法律诉讼3
        url = "http://www.tianyancha.com/v2/getlawsuit/" + comName + ".json?page=1&ps=10";
        // {"state":"ok","message":"","data":{"total":3,"items":[{"title":"山东华凌电缆有限公司与无棣鑫岳化工有限公司买卖合同纠纷一审民事裁定书","submittime":"1420560000000","court":"无棣县人民法院","casetype":"民事案件","uuid":"9fe879f21cb811e6b554008cfae40dc0","doctype":"民事裁定书","url":"http://wenshu.court.gov.cn/content/content?DocID=70f69737-7c64-4dd5-abe4-1b59cea8866a","caseno":"（2015）棣商初字第8号"},{"title":"山东华凌电缆有限公司与山东华氟化工有限责任有限公司买卖合同纠纷一审民事判决书","submittime":"0","court":"章丘市人民法院","casetype":"民事案件","uuid":"d4b5444ebd1b41eaa357ab56e3078fbe","doctype":"民事判决书","url":"http://wenshu.court.gov.cn/content/content?DocID=08626a68-1486-43d6-a367-81b49db8f894","caseno":"（2016）鲁0181民初3395号"},{"title":"济南建工总承包集团建筑工程有限公司与山东华凌电缆有限公司买卖合同纠纷二审民事判决书","submittime":"0","court":"山东省济南市中级人民法院","casetype":"民事案件","uuid":"917fa05776dd4b659105858e662531a1","doctype":"民事判决书","url":"http://wenshu.court.gov.cn/content/content?DocID=bdc65945-33b6-4e4c-9fbd-c46da9e53d89","caseno":"（2016）鲁01民终3382号"}],"pagesize":10}}
        /**
         * error
         */
        url = "http://dis.tianyancha.com/dis/getInfoById/" + comId + ".json?";
        // 招聘信息
        url = "http://www.tianyancha.com/extend/getEmploymentList.json?companyName=" + comName + "&pn=1&ps=10";
        // {"state":"ok","message":"","data":{"companyEmploymentList":[{"id":183208100,"title":"业务总监","city":"济南","district":"济南","companyName":"山东华凌电缆有限公司","oriSalary":"面议","urlPath":"http://jn.hbrc.com/Company/detailcompInfo-68230-280092.html","startdate":1488643200000,"enddate":1491235200000,"source":"博才网","education":"大专","employerNumber":"","experience":"3-5年,5-10年,10年以上","createTime":1488754470000,"updateTime":1488872931000,"description":"工作职责br参与公司战略研究根据公司经营战略目标，提供公司年度经营预算，制订业务工作方针政策，提供公司内部业务管理改进方案并贯彻实施组织市场调研，收集有关市场信息，分析内外环境，确定目标市场，收集分析竞争对象信息，制订公司竞争策略并组织实施组织产品的定位及开发维持开拓业务渠道，不断扩大市场份额维护与关键客户的联系，参与重大业务洽谈，解决业务拓展中的重大问题，以此提升公司品牌形象。brbr职位职责br1完成公司年度业务目标以及其他任务br2有独立的业务渠道，具有良好的市场拓展能力br3负责业务部内部的部分管理及建设br4培训市场调查与新市场机会的发现br5新项目市场推广方案的制定br6成熟项目的业务组织协调和业务绩效管理br7业务员队伍的建设与培养等。"},{"id":182597202,"title":"济南市会计招聘","city":"济南","district":"章丘市","companyName":"山东华凌电缆有限公司","oriSalary":"面议","urlPath":"http://www.dazhonghr.com/company/pos_1295515.shtml","startdate":1488384000000,"enddate":1491062400000,"source":"大众人才网","education":"大专","employerNumber":"2人","experience":"不限","createTime":1488498651000,"updateTime":1489553827000,"description":"1、审批财务收支，审阅财务专题报告和会计报表，对重大的财务收支计划、经济合同进行会签；\n2、编制预算和执行预算，参与拟订资金筹措和使用方案，确保资金的有效使用；\n3、审查公司对外提供的会计资料；\n4、负责审核公司本部和各下属单位上报的会计报表和集团公司会计报表，编制财务综合分析报告和专题分析报告，为公司领导决策提供可靠的依据；\n5、制订公司内部财务、会计制度和工作程序，经批准后组织实施并监督执行；\n6、组织编制与实现公司的财务收支计划、信贷计划与成本费用计划。\n1、会计相关专业，大专以上学历；\n2、有一般纳税人企业工作经验者优先；\n3、认真细致，爱岗敬业，吃苦耐劳，有良好的职业操守；\n4、思维敏捷，接受能力强，能独立思考，善于总结工作经验；\n5、熟练应用财务及Office办公软件，对金蝶、用友等财务系统有实际操作者优先；\n6、具有良好的沟通能力；\n7、有会计从业资格证书，同时具备会计初级资格证者优先考虑。"},{"id":182253777,"title":"济南市行政助理招聘","city":"济南","district":"济南","companyName":"山东华凌电缆有限公司","oriSalary":"面议","urlPath":"http://www.dazhonghr.com/company/pos_1158898.shtml","startdate":1488211200000,"enddate":1503849600000,"source":"大众人才网","education":"本科","employerNumber":"1人","experience":"不限","createTime":1488286484000,"updateTime":1489604499000,"description":"1、负责总经理的办公服务工作。\n2、负责组织撰写或校对以公司名义上报外发的综合性的文字材料；负责组织起草总经理会议材料。\n3、负责公司文秘业务指导。\n4、负责公司办公会的有关事宜，并办理会议议定事项。\n5、负责督促、检查、催办上级批件，公司领导批件及总经理办公会议定事项的办理工作。\n6、负责纪录、整理总经理办公会会议纪要。\n7、负责文书收发、运转、检查、指导有文档工作的业务管理，负责保密工作。\n8、完成领导交办的其他工作。\n行政管理、中文、文秘等相关专业\n身高175以上。"},{"id":182253778,"title":"济南市标书制作招聘","city":"济南","district":"章丘市","companyName":"山东华凌电缆有限公司","oriSalary":"3000-5000","urlPath":"http://www.dazhonghr.com/company/pos_1295542.shtml","startdate":1488211200000,"enddate":1490630400000,"source":"大众人才网","education":"大专","employerNumber":"2人","experience":"不限","createTime":1488286484000,"updateTime":1489604267000,"description":"（1）负责招投标信息收集、投标文件制作及标书涉及的相应工作；\n（2）负责与合作单位建立良好的合作关系，协助部门经理处理内外协调联系；\n（3）负责投标中与公司其他部门沟通协作；\n（4）负责投标项目相关文档资料的整理和归档；\n（5）负责领导交办的部门其它日常事务性工作。\n（1）专科及以上学历；\n（2）熟练使用office办公软件；\n（3）一年以上标书制作或招投标工作经验，熟悉招投标流程、标书制作流程及工艺要求；\n（4）具有较强的保密意识，沟通、协调、组织能力强，工作认真、有条理、细致、责任心强，具有团队协作精神。"},{"id":180552198,"title":"济南网络管理员招聘","city":"济南","district":"章丘市","companyName":"山东华凌电缆有限公司","oriSalary":"面议","urlPath":"http://jobs.51zpyc.com/qymm/jb-864322.shtml","startdate":1487520000000,"enddate":1494432000000,"source":"51招聘英才网","education":"大专","employerNumber":"2","experience":"不限","createTime":1487564369000,"updateTime":1489553875000,"description":"岗位职责：1、负责内部局域网络维护；2、进行小型机、服务器、路由器等设备管理，以及网络平台的运行监控和维护；3、进行办公设备的日常维护及管理；技术档案维护；4、负责病毒的查杀，维护网络系统安全；5、处理网络及计算机故障；6、负责内部信息系统建设、维护；进行域名、后台数据、邮箱管理。岗位要求：1、计算机或IT相关专业，大学本科，25岁以下2、一年的网络管理、服务器网管工作经验；3、熟悉路由器，交换机、防火墙的网络设备的设置与管理；4、了解操作"},{"id":180552200,"title":"济南人力资源专员招聘","city":"济南","district":"章丘市","companyName":"山东华凌电缆有限公司","oriSalary":"面议","urlPath":"http://jobs.51zpyc.com/qymm/jb-852438.shtml","startdate":1487520000000,"enddate":1494432000000,"source":"51招聘英才网","education":"本科","employerNumber":"3~5","experience":"[\"1-3年\",\"3-5年\",\"5-10年\",\"10年以上\"]","createTime":1487564369000,"updateTime":1489553871000,"description":"岗位职责：1、参与组织公司所需人员的招聘、培训、考核等事宜。2、负责办理部门间的人员调动手续。3、参与公司员工培训、激励工作。4、参与处理公司劳动关系，签订劳动合同，社会保险事宜。5、负责公司人事档案管理工作。6、参与修订公司有关人力资源方面的管理制度。7、协助上级领导完成工作。岗位要求：本科及以上学历，男女不限，要求人力资源相关专业，1年以上相关工作经验，耐心、细心、有责任心，有较强的适应力、亲和力及学习能力。"},{"id":180552197,"title":"策划文案","city":"济南","district":"济南","companyName":"山东华凌电缆有限公司","oriSalary":"面议","urlPath":"http://jn.hbrc.com/Company/detailcompInfo-68230-280093.html","startdate":1487433600000,"enddate":1490025600000,"source":"博才网","education":"大专","employerNumber":"","experience":"[\"1-3年\",\"3-5年\",\"5-10年\",\"10年以上\"]","createTime":1487564369000,"updateTime":1487564369000,"description":"br岗位职责br1负责商业情报的收集及信息平台的规划，研究市场的宏观方面的信息，包含市场动态竞争品牌动向产品与市场信息br2参与制定年季月度市场推广方案并督导执行br3参与完成广告策划方案品牌推广方案方案设计报告的撰写br4协调公司内部的运作实施，并完成品牌产品推广的效果评估，提出改进方案。br任职资格br1市场营销广告管理类或相关专业专科以上学历br2具有快速消费品工业产品的从业背景优先，有一年以上市场策划工作经验br3优秀的文案功底，有较强的创造性思维能力创意概念及良好的沟通能力br4了解市场动态，依据市场变化适时策划制定整体促销方案，策划定期的促销活动br5有一定的组织实施经验，监督指导落实促销活动的执行，有成功的策划案例者优先br6有综合运用包括广告策划软文宣传公关活动等在内的各种营销方式进行市场宣传品牌推广的能力br7熟练操作办公软件"},{"id":180552199,"title":"货运司机","city":"济南","district":"济南","companyName":"山东华凌电缆有限公司","oriSalary":"面议","urlPath":"http://jn.hbrc.com/Company/detailcompInfo-68230-280095.html","startdate":1487433600000,"enddate":1490025600000,"source":"博才网","education":"不限","employerNumber":"","experience":"[\"3-5年\",\"5-10年\",\"10年以上\"]","createTime":1487564369000,"updateTime":1487564369000,"description":"录用后第一个为实习期，工资每月1500元管吃管住，实习完毕后每月2300元另加奖金。"},{"id":176151311,"title":"济南市办公室主任招聘","city":"济南","district":"济南","companyName":"山东华凌电缆有限公司","oriSalary":"2000元以上","urlPath":"http://www.qlrc.com/personal/jb4FDFB60B0E.html","startdate":1487260800000,"enddate":1487606400000,"source":"齐鲁人才网","education":"本科","employerNumber":"1人","experience":"不限","createTime":1487341627000,"updateTime":1489234386000,"description":"1.对文件能进行很好的归档管理；2.外语口语熟练，能准确的对英语或俄语文件翻译，执行能力强；对外沟通交流能力强；3.工作认真细致、责任心强，品行端正、有较强亲和力。\n英语四级及以上、俄语六级；行政管理等相关专业，熟练掌握常用办公软件."},{"id":175610709,"title":"济南市车床工招聘","city":"济南","district":"济南","companyName":"山东华凌电缆有限公司","oriSalary":"面议","urlPath":"http://www.dazhonghr.com/company/pos_1158237.shtml","startdate":1487260800000,"enddate":1502899200000,"source":"大众人才网","education":"不限","employerNumber":"1人","experience":"不限","createTime":1487321124000,"updateTime":1489221356000,"description":"1、按时完成产品或工艺所在环节分配的生产任务；\n\n2、严格按照机床操作规程和机床使用说明书的要求使用机床； \n\n3、严格按照工艺文件和图纸加工工件，正确填写工序作业程序单和其他质量记录；\n\n4、负责机床的日常维护保养；\n\n5、工作中，改进自我水平并且就生产过程中的问题提出建议。\n1、从事普通数显铣床实际操作多年，熟悉各种材质加工特性；\n\n2、熟练操作磨床，钻床者、具有机加工工作经验者优先；\n\n3、踏实肯干，吃苦耐劳，干活快"}],"totalRows":91}}
        // 税务评级2
        url = "http://www.tianyancha.com/expanse/taxcredit.json?id=" + comId + "&ps=5&pn=1";
        // {"state":"ok","message":"","data":{"count":2,"items":[{"grade":"A","year":"2015","evalDepartment":"国家税务总局","type":"国税","idNumber":"370181720768114","name":"山东华凌电缆有限公司"},{"grade":"A","year":"2014","evalDepartment":"国家税务总局","type":"国税","idNumber":"370181720768114","name":"山东华凌电缆有限公司"}]}}
        // 商标信息3
        url = "http://www.tianyancha.com/tm/getTmList.json?id=" + comId + "&pageNum=1&ps=5";
        // {"state":"ok","message":"","data":{"viewtotal":"3","items":[{"id":"6820504","intCls":"09-软件产品、科学仪器","category":"注册申请部分驳回","tmPic":"http://tm-image.tianyancha.com/tm/7002fe51d34eff78209261996aa6c3ee.jpg","status":"有效","appDate":"1271347200000","regNo":"8214930","applicantCn":"山东华凌电缆有限公司","tmName":"华凌 HL"},{"id":"6820579","intCls":"43-餐饮、住宿服务","category":"注册申请初步审定","tmPic":"http://tm-image.tianyancha.com/tm/a939939802a626cc7c43b8c9183ebfe8.jpg","status":"有效","appDate":"1271347200000","regNo":"8214934","applicantCn":"山东华凌电缆有限公司","tmName":"华凌 HL"},{"id":"1298903","intCls":"09-软件产品、科学仪器","category":"变更商标申请人/注册人名义/地址中","tmPic":"http://tm-image.tianyancha.com/tm/6e9b0a194cfb9328a4458125a972b739.jpg","status":"有效","appDate":"978624000000","regNo":"1710152","applicantCn":"山东华凌电缆有限公司","tmName":"华凌"}],"pageSize":"5"}}
        // 专利66
        url = "http://www.tianyancha.com/expanse/patent.json?id=" + comId + "&pn=1&ps=5";
        // {"state":"ok","message":"","data":{"viewtotal":"66","items":[{"mainCatNum":"H01B7/18(2006.01)I","createTime":"1483147523000","updateTime":"0","applicationPublishNum":"CN106098212A","agency":"济南圣达知识产权代理有限公司 37221","pid":"7586f28d27d94e08a4361e94b6c0d5a8","inventor":"王兆波;苏风清;蔡延玮;宋怀旭;连瑞琦;","agent":"张勇;","applicationPublishTime":"2016.11.09","imgUrl":"http://pic.cnipr.com:8080/XmlData/FM/20161109/201610737407.1/201610737407.gif","patentNum":"CN201610737407.1","allCatNum":"H01B7/18(2006.01)I;H01B7/28(2006.01)I;H01B9/00(2006.01)I;H01B13/00(2006.01)I;","patentName":"一种充电桩用耐磨电缆及其制造方法","abstracts":"本发明公开了一种充电桩用耐磨电缆及其制造方法，包括电缆内芯和包在电缆内芯外的电缆外层，所述电缆内芯包括电源电缆、保护接地电缆、中线电缆、一对控制/充电连接确认电缆及一对备用扩展电缆，所述一对控制/充电连接确认电缆外包有双屏蔽层；所述电缆外层由内向外依次为包带、TPE内护套及TPU外护套，所述TPE内护套横截面为圆形，TPU外护套横截面为凹凸状。本发明结构紧凑、稳定性好，无卤低烟、无毒、耐腐蚀、耐拉伸，耐弯曲、耐摩擦等特点。护套层采用TPE及TPU护套具有硬度范围广，有优良的着色性，触感柔软，耐候性，抗疲劳性和耐温性及机械性能优良。","address":"250220 山东省济南市历城区经十东路圣井高科技园十号路7号","applicationTime":"2016.08.26","uuid":"7586f28d27d94e08a4361e94b6c0d5a8","patentType":"发明专利","applicantName":"山东华凌电缆有限公司;"},{"mainCatNum":"H01B7/02(2006.01)I","createTime":"1483157065000","updateTime":"0","applicationPublishNum":"CN104240810B","agency":"济南泉城专利商标事务所 37218","pid":"1481e192e4d0446fab0ec73c1d3a9dcc","inventor":"王兆波;宋怀旭;潘茂龙;李广杰;王清保;王秀玉;","agent":"纪艳艳;","applicationPublishTime":"2016.08.24","imgUrl":"http://pic.cnipr.com:8080/XmlData/SQ/20160824/201410424852.3/201410424852.gif","patentNum":"CN201410424852.3","allCatNum":"H01B7/02(2006.01)I;H01B7/17(2006.01)I;H01B7/295(2006.01)I;H01B7/28(2006.01)I;H01B13/00(2006.01)I;","patentName":"三代非能动核电站和缓环境用1E级电缆的生产方法","abstracts":"本发明提供了一种三代非能动核电站和缓环境用1E级电缆及生产方法，1E级电缆，包括一根以上导体，导体外壁挤包异质双层共挤绝缘层并绕包有包带构成线芯且置于填充体内，填充体外包裹屏蔽层、隔氧层或内护套层和外护套层。所述的1E级电缆的生产方法，包括以下步骤：包括导体选料?绕包?内绝缘层和外绝缘层挤包?内绝缘层和外绝缘层辐照交联处理?成缆?填充层挤包?屏蔽?内护套层或隔氧层挤包?外护套层挤包?外护套层辐照交联处理。","address":"250220 山东省济南市章丘市经十东路圣井高科技园十号路七号","applicationTime":"2014.08.26","uuid":"1481e192e4d0446fab0ec73c1d3a9dcc","patentType":"发明专利","applicantName":"山东华凌电缆有限公司;"},{"mainCatNum":"H01B11/06(2006.01)I","createTime":"1483151659000","updateTime":"0","applicationPublishNum":"CN104157365B","agency":"济南泉城专利商标事务所 37218","pid":"d5478396c41e4493843409988179f17a","inventor":"宋怀旭;李广杰;潘茂龙;李彩霞;孙丛丛;","agent":"纪艳艳;","applicationPublishTime":"2016.08.03","imgUrl":"http://pic.cnipr.com:8080/XmlData/SQ/20160803/201410427049.5/201410427049.gif","patentNum":"CN201410427049.5","allCatNum":"H01B11/06(2006.01)I;H01B7/02(2006.01)I;H01B7/295(2006.01)I;H01B7/28(2006.01)I;H01B13/00(2006.01)I;","patentName":"三代非能动核电站和缓环境1E级控制电缆及生产方法","abstracts":"本发明提供一种三代非能动核电站和缓环境用1E级控制电缆及生产方法。三代非能动核电站和缓环境1E级控制电缆，包括一根导体，导体外壁挤包内绝缘层和外绝缘层并绕包有包带构成线芯，多根线芯置于填充体内绞合成缆芯，缆芯由内至外依次设置有包带层、复合屏蔽层、绕包层和护套层。所述的1E级控制电缆的生产方法，包括以下步骤：包括导体绞合?内绝缘层和外绝缘层挤包?内绝缘层和外绝缘层辐照交联处理?绝缘绕包?成缆?填充层挤包?屏蔽?护套层挤包?护套层辐照交联处理。","address":"250220 山东省济南市章丘市经十东路圣井高科技园十号路七号","applicationTime":"2014.08.26","uuid":"d5478396c41e4493843409988179f17a","patentType":"发明专利","applicantName":"山东华凌电缆有限公司;"},{"mainCatNum":"H01B9/02(2006.01)I","createTime":"1483149685000","updateTime":"0","applicationPublishNum":"CN104183331B","agency":"济南泉城专利商标事务所 37218","pid":"79e3ab9b44854ebfb9fbf98568b82f02","inventor":"潘茂龙;宋怀旭;李广杰;李晓宇;孙丛丛;","agent":"纪艳艳;","applicationPublishTime":"2016.06.22","imgUrl":"http://pic.cnipr.com:8080/XmlData/SQ/20160622/201410425255.2/201410425255.gif","patentNum":"CN201410425255.2","allCatNum":"H01B9/02(2006.01)I;H01B7/02(2006.01)I;H01B7/29(2006.01)I;H01B7/28(2006.01)I;H01B7/18(2006.01)I;H01B7/22(2006.01)I;H01B1/02(2006.01)I;H01B13/00(2006.01)I;","patentName":"三代非能动核电站和缓环境1E级电力电缆及生产方法","abstracts":"本发明提供一种三代非能动核电站和缓环境用1E级电力电缆及生产方法。一种三代非能动核电站和缓环境1E级电力电缆，包括复数个线芯；线芯包括一根以上导体，每根导体外壁绕包有第一包带，第一包带外挤包内绝缘层和外绝缘层并绕包有第二包带；线芯和一个以上填芯绞合成缆芯；缆芯由内至外依次设置有包带层、填充层、内屏蔽层、外屏蔽层、绕包层和护套层。所述的1E级电力电缆的生产方法，包括以下步骤：导体绞合-导体绕包-内绝缘层和外绝缘层挤包-内绝缘层和外绝缘层辐照交联处理-绝缘绕包-线芯和填芯成缆-填充层挤包-屏蔽-护套层挤包-护套层辐照交联处理。","address":"250220 山东省济南市章丘市经十东路圣井高科技园十号路七号","applicationTime":"2014.08.26","uuid":"79e3ab9b44854ebfb9fbf98568b82f02","patentType":"发明专利","applicantName":"山东华凌电缆有限公司;"},{"mainCatNum":"H01B7/00(2006.01)I","createTime":"1483160303000","updateTime":"0","applicationPublishNum":"CN205211435U","agency":"济南圣达知识产权代理有限公司 37221","pid":"3872eabadacb4e539ab187ed25a4cc66","inventor":"潘茂龙;蔡延玮;连瑞琦;翟孟刚;宋怀旭;","agent":"赵妍;","applicationPublishTime":"2016.05.04","imgUrl":"http://pic.cnipr.com:8080/XmlData/XX/20160504/201521087640.7/201521087640.gif","patentNum":"CN201521087640.7","allCatNum":"H01B7/00(2006.01)I;H01B7/02(2006.01)I;H01B7/18(2006.01)I;H01B7/28(2006.01)I;H01B7/282(2006.01)I;H01B7/29(2006.01)I;H01B13/00(2006.01)I;H01B13/14(2006.01)I;","patentName":"长寿命预分支电缆","abstracts":"本实用新型公开了长寿命预分支电缆，该电缆包括被注塑为一体的主电缆和分支电缆，主电缆通过联接体联接分支电缆，主电缆与分支电缆的联接处设有包裹层，包裹层在与主电缆和分支电缆的接口处设有加强层，电缆采用国内先进的辐照交联技术增加了电缆的机械性能及抗辐射性能。绝缘挤出技术采用我公司自主研发的双层共挤技术此技术既保证了电缆的机械性能还提高了电缆的电气性能，从而达到电缆的长寿命效果。本实用新型中的电缆使用寿命长，一次施工，无需更换，能降低电缆的维修费用和更换费用。敷设简单，安装便捷，使用环境要求低，可直接敷设于电缆沟内、建筑的专用电缆竖井内，也可敷设于不同的电缆桥架中，安装劳动强度小，施工周期短。","address":"250220 山东省济南市历城区经十东路圣井高科技园十号路7号","applicationTime":"2015.12.23","uuid":"3872eabadacb4e539ab187ed25a4cc66","patentType":"实用新型","applicantName":"山东华凌电缆有限公司;"}],"pageSize":"5"}}
        // 网站备案1
        url = "http://www.tianyancha.com/v2/IcpList/" + comId + ".json";
        // {"state":"ok","message":"","data":[{"companyType":"企业","webName":"山东华凌电缆有限公司","liscense":"鲁ICP备10203337号","companyName":"山东华凌电缆有限公司","webSite":["www.hl-cable.com"],"examineDate":"2011-03-30"}]}
        url = "http://pv.tianyancha.com/pv?url=/company/" + comId + "";
        // 41
        
        Map<String, String> headerMap = new HashMap<String, String>();
        // headerMap.put("Host", "www.tianyancha.com");
        // headerMap.put("Referer", "http://www.tianyancha.com/company/233174024");
        // headerMap.put("Tyc-From", "normal");
        // headerMap.put("User-Agent",
        // "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36");
        headerMap.put("Accept", "application/json, text/plain, */*");
        // headerMap.put("Accept-Encoding", "gzip, deflate, sdch");
        // headerMap.put("Accept-Language", "zh-CN,zh;q=0.8");
        // headerMap.put("Cache-Control", "max-age=0");
        // headerMap.put("Connection", "keep-alive");
        
        ret = HttpClientUtil.httpGet(url, headerMap, "UTF-8");
        System.out.println(ret);
        
        System.out.println("--------------------------");
    }
    
    private static void testget1() {
        String url = "";
        String ret = "";
        // 测试GET 方法
        url = "http://www.sdbys.cn/col/col21/index.html";
        ret = HttpClientUtil.httpGet(url, null, "UTF-8");
        System.out.println(ret);
        
        System.out.println("--------------------------");
    }
    
    private static void testpost1() {
        String url = "";
        String ret = "";
        System.out.println("begin.....");
        // 测试Post 方法
        url = "http://www.sdjs.gov.cn/module/jslib/jquery/jpage/dataproxy.jsp?startrecord=1&endrecord=45&perpage=15";
        Map<String, String> paraMap = new HashMap<String, String>();
        paraMap.put("Content-Type", "text/xml;charset=UTF-8");
        // paraMap.put("Accept", "application/xml, text/xml, */*; q=0.01");
        // paraMap.put("Accept-Encoding", "gzip, deflate");
        // paraMap.put("Accept-Language:", "zh-CN,zh;q=0.8");
        // paraMap.put("Content-Type",
        // "application/x-www-form-urlencoded; charset=UTF-8");
        paraMap.put("Referer", "http://www.sdjs.gov.cn/col/col5/index.html");
        // paraMap.put("User-Agent:",
        // "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36");
        // paraMap.put("X-Requested-With", "XMLHttpRequest");
        
        Map<String, String> bodyMap = new HashMap<String, String>();
        bodyMap.put("col", "1");
        bodyMap.put("appid", "1");
        bodyMap.put("webid", "1");
        bodyMap.put("path", "/");
        bodyMap.put("columnid", "5");
        bodyMap.put("sourceContentType", "1");
        bodyMap.put("unitid", "127");
        // bodyMap.put("webname", "山东省住房和城乡建设厅");
        // bodyMap.put("permissiontype", "0");
        
        ret = HttpClientUtil.httpPost(url, paraMap, bodyMap, "UTF-8");
        System.out.println(ret);
        System.out.println("end .....");
    }
}