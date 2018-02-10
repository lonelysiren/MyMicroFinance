package com.mf.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.html2pdf.resolver.font.DefaultFontProvider;
import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.layout.element.IElement;
import com.itextpdf.layout.font.FontProvider;

public class iTextTest {
	 public static final String FONT="./font/simfang.ttf";
	public static void main(String[] args) {
	     // IO
        File htmlSource = new File("input.html");
        File pdfDest = new File("output.pdf");
        String html = "<h2 style=\"text-align:center;font-size:20px;font-weight: bold\">第三方借款协议</h2>\r\n" + 
        		"\r\n" + 
        		"<p>&nbsp;</p>\r\n" + 
        		"\r\n" + 
        		"<p><br />\r\n" + 
        		"甲方(资金出借方)：<br />\r\n" + 
        		"<br />\r\n" + 
        		"身份证号：<br />\r\n" + 
        		"<br />\r\n" + 
        		"乙方（资金借入方）：擦 ___________________ (正楷签名)<br />\r\n" + 
        		"<br />\r\n" + 
        		"身份证号：431003199408136018 ___________________ (填写身份证号)<br />\r\n" + 
        		"<br />\r\n" + 
        		"现住地址：123<br />\r\n" + 
        		"<br />\r\n" + 
        		"手机号：13762657891<br />\r\n" + 
        		"<br />\r\n" + 
        		"丙方（服务提供商）：湖北天诚信息服务有限公司<br />\r\n" + 
        		"<br />\r\n" + 
        		"地址：湖北<br />\r\n" + 
        		"<br />\r\n" + 
        		"联系方式：13845611234<br />\r\n" + 
        		"<br />\r\n" + 
        		"<span style=\"font-weight:bold\">鉴于：</span><br />\r\n" + 
        		"<br />\r\n" + 
        		"1.&emsp; 丙方是一家合法成立并有效存在的公司，公司名称为湖北天诚信息服务有限公司,<br />\r\n" + 
        		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 提供投资和信用咨询，为交易提供信息服务，企业管理服务，广告信息发布，商务信<br />\r\n" + 
        		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 息咨询服务资质。<br />\r\n" + 
        		"2.&emsp;甲乙双方确认本协议是通过丙方签署，基于上述事实，甲乙双方就借款事宜达成协议如下。<br />\r\n" + 
        		"<br />\r\n" + 
        		"<span style=\"font-weight:bold;font-size:16px\">一、&emsp;借款及偿还方式</span><br />\r\n" + 
        		"<br />\r\n" + 
        		"1.1&emsp;甲方承诺其出借资金为合法所得，甲方对该资金享有完整且无瑕疵的支配权。<br />\r\n" + 
        		"1.2&emsp;乙方承诺借款为自己资金周转使用，不用于自己或转借他人进行炒股，买卖期货，等及法<br />\r\n" + 
        		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;律禁止的不合法用途。<br />\r\n" + 
        		"1.3&emsp;乙方向甲方借款，借款信息如下</p>\r\n" + 
        		"\r\n" + 
        		"<p>&nbsp;</p>\r\n" + 
        		"\r\n" + 
        		"<table border=\"1\" cellpadding=\"1\" cellspacing=\"1\" class=\"table table-bordered comtable thcenter\" width=\"500\">\r\n" + 
        		"	<thead>\r\n" + 
        		"		<tr align=\"center\">\r\n" + 
        		"			<td>借款总金额：</td>\r\n" + 
        		"			<td>￥：10000</td>\r\n" + 
        		"		</tr>\r\n" + 
        		"	</thead>\r\n" + 
        		"	<tbody>\r\n" + 
        		"		<tr align=\"center\">\r\n" + 
        		"			<td>大写：</td>\r\n" + 
        		"			<td>壹萬元整</td>\r\n" + 
        		"		</tr>\r\n" + 
        		"	</tbody>\r\n" + 
        		"</table>\r\n" + 
        		"\r\n" + 
        		"<p>&nbsp;</p>\r\n" + 
        		"\r\n" + 
        		"<table border=\"1\" cellpadding=\"1\" cellspacing=\"1\" class=\"table table-bordered comtable thcenter\" width=\"500\">\r\n" + 
        		"	<tbody>\r\n" + 
        		"		<tr align=\"center\">\r\n" + 
        		"			<td rowspan=\"2\">协议期限：</td>\r\n" + 
        		"			<td>2017-12-03</td>\r\n" + 
        		"		</tr>\r\n" + 
        		"		<tr align=\"center\">\r\n" + 
        		"			<td>2018-02-22</td>\r\n" + 
        		"		</tr>\r\n" + 
        		"	</tbody>\r\n" + 
        		"</table>\r\n" + 
        		"\r\n" + 
        		"<p><br />\r\n" + 
        		"1.4 .&emsp;借款说明<br />\r\n" + 
        		"<br />\r\n" + 
        		"1.4.1&nbsp;&emsp;本协议期限内甲方向乙方提供最高不超过人民币（￥10000）大写壹萬元整元）<br />\r\n" + 
        		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;的借款，在本协议约定的期限内，乙方可随时在甲方提供的额度内申请借款，但乙<br />\r\n" + 
        		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;方单次或多次累计借款不得超过甲方提供的借款额度.<br />\r\n" + 
        		"1.4.2.&emsp; 借款周期：借款周期为七天，乙方应在合同约定的日期前还清本次借款，<br />\r\n" + 
        		"1.4.3.&emsp; 还款说明：乙方应根据实际的借款总额按天计息，自借款达到乙方相关账号之日起计算<br />\r\n" + 
        		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;利息，协议期内乙方在还清单次借款后可以根据自身的资金需求重复向甲方提出借款。<br />\r\n" + 
        		"<br />\r\n" + 
        		"1.5 .&emsp;相关费用<br />\r\n" + 
        		"<br />\r\n" + 
        		"1.5.1.&emsp;乙方确认：就单次一个周期即七天乙方借款应向丙方支付260元的账户管理费，用于相关<br />\r\n" + 
        		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;账户的管理，该借款费用包括乙方支付给丙方的借款服务费，其具体由手机验证费,<br />\r\n" + 
        		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;银行卡验证费,身份证验证费,信用审核费,信息发布费,撮合服务费,平台运营费,<br />\r\n" + 
        		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;客户端使用费等组成，乙方同意上述服务费用从本金中先行扣除。用于相关账户的<br />\r\n" + 
        		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;管理。<br />\r\n" + 
        		"1.5.2.&emsp;乙方还款金额及还款方式详见协议附件（还款明细说明）<br />\r\n" + 
        		"1.5.3.&emsp;乙方确保及时还款至协议唯一指定账号，如因乙方还款不及时或还款至非协议账户而造<br />\r\n" + 
        		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;成的逾期所产生的费用，乙方应按照协议内容，支付滞纳金。<br />\r\n" + 
        		"<br />\r\n" + 
        		"<span style=\"font-weight:bold;font-size:16px\">二、&emsp;借款的获取来源</span><br />\r\n" + 
        		"<br />\r\n" + 
        		"2.1.&nbsp;&nbsp;&emsp;乙方所获借款可能会来自多位出借人，丙方或第三方机构会按出借人列表所示出借比例对<br />\r\n" + 
        		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;该笔还款为相应的出借人进行还款分配。<br />\r\n" + 
        		"<br />\r\n" + 
        		"<span style=\"font-weight:bold;font-size:16px\">三． &emsp;结算和支付方式</span><br />\r\n" + 
        		"<br />\r\n" + 
        		"3.1.&nbsp;&nbsp;&nbsp;&emsp;乙方同意按协议附件（还款明细说明）所示还款日期，周期，金额及还款账号如期进行还款<br />\r\n" + 
        		"3.2.&nbsp;&nbsp;&nbsp;&emsp;甲方授权丙方负责本协议下的借款回收及日常管理工作，甲乙双方均同意丙方有权代甲方进行借款<br />\r\n" + 
        		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;的违约提醒及催收工作，包括但不限于电话通知，发律师函，对乙方提起诉讼等，<br />\r\n" + 
        		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;乙方对上述委托的提醒，催收事项明确知晓并积极配合。<br />\r\n" + 
        		"<br />\r\n" + 
        		"<span style=\"font-weight:bold;font-size:16px\">四．&emsp;提前还款</span><br />\r\n" + 
        		"<br />\r\n" + 
        		"4.1 .&nbsp;&nbsp;&nbsp;&emsp;乙方可随时提前还款，如提前还款，甲方应退还未到期所收利息，丙方收取的管理费不予退款。<br />\r\n" + 
        		"<br />\r\n" + 
        		"<span style=\"font-weight:bold;font-size:16px\">五．&emsp;违约责任</span><br />\r\n" + 
        		"<br />\r\n" + 
        		"5.1.&nbsp;&nbsp;&nbsp;&emsp;若乙方在还款期限之前未按照合同约定的收支账户转入还款金额或金额不足，即构成违约，<br />\r\n" + 
        		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;并向甲方、丙方承担以下违约责任：<br />\r\n" + 
        		"5.1.1 .&emsp;甲方自应还款日起每日按实际到账金额的10%收取滞纳金。每周期即七天计算复利。<br />\r\n" + 
        		"5.1.2.&emsp;乙方如因特殊原因暂时无法按时足额还款时,请在第一时间(当期还款日之前)与本公司<br />\r\n" + 
        		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;联系。我们将协助您安排预约还款并准确告知您还款金额(本息,服务费，违约金,滞<br />\r\n" + 
        		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;纳金，罚息等协议约定的相关费用等)特别需要您值的注意的是:针对违约时长超过<br />\r\n" + 
        		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;15天，将支付全部借款金额、利息、账户管理费、逾期违约金、罚息及协议约定的<br />\r\n" + 
        		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;相关费用。<br />\r\n" + 
        		"5.1.3.&emsp;逾期超过30日，丙方有权对乙方提供的及公司自行收集的乙方个人信息和资料编辑<br />\r\n" + 
        		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;入网站黑名单，并将该黑名单对第三方披露，并与任何第三方数据共享，以便丙<br />\r\n" + 
        		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;方或其委托的第三方催收逾期借款及对乙方的其他申请进行审核之用，由此因第三<br />\r\n" + 
        		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;方的行为可能造成乙方的损失，甲方与丙方不承担法律责任。<br />\r\n" + 
        		"5.2 &nbsp;&nbsp;&nbsp;.&emsp;守约方为追回损失而支付的包括但不限于律师费、诉讼费、公证费、交通通讯费等，由违 约方承担。<br />\r\n" + 
        		"<br />\r\n" + 
        		"<span style=\"font-weight:bold;font-size:16px\">六、&emsp;税务</span><br />\r\n" + 
        		"<br />\r\n" + 
        		"&nbsp;&emsp;协议各方在资金出借、转让过程中产生的税费，自行向主管税务机关申报、缴纳。<br />\r\n" + 
        		"<br />\r\n" + 
        		"<span style=\"font-weight:bold;font-size:16px\">七、&emsp;通知及送达。</span><br />\r\n" + 
        		"<br />\r\n" + 
        		"7.1.&nbsp;&nbsp;&nbsp;&emsp;在本协议有效期内，因法律、法规、政策的变化，或任一方丧失履行本协议的资格和/或<br />\r\n" + 
        		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;能力，影响本协议履行的，该方应承担在合理时间内通知其他各方的义务。<br />\r\n" + 
        		"7.2.&nbsp;&nbsp;&nbsp;&emsp;协议各方同意，与本协议有关的任何通知，以书面方式送达方为有效。书面形式包括但不<br />\r\n" + 
        		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;限于：传真、快递、邮件、电子邮件。上述通知应当被视为在下列时候送达：以传<br />\r\n" + 
        		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;真发送，为该传真成功发送并接收方收到之日，以快递或专人发送，为收件人收<br />\r\n" + 
        		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;到该通知日，以挂号邮件发送，为发出7个工作日；以电子邮件发送，为电子邮件<br />\r\n" + 
        		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;成功发出之日。<br />\r\n" + 
        		"<br />\r\n" + 
        		"<span style=\"font-weight:bold;font-size:16px\">八、&emsp;协议的变更、终止和解除</span><br />\r\n" + 
        		"<br />\r\n" + 
        		"除本协议或法律另有规定外，协议的变更、解除和终止以下列约定为准。<br />\r\n" + 
        		"<br />\r\n" + 
        		"8.1.&nbsp;&nbsp;&nbsp;&emsp;本协议的任何修改、变更应协议各方另行协商，并就修改、变更事项共同签署书面协议后<br />\r\n" + 
        		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 方可成立。<br />\r\n" + 
        		"&emsp;8.2.&nbsp;&nbsp;.&emsp; 本协议在下列情况下解除：<br />\r\n" + 
        		"8.2.1.&nbsp;&emsp;经双方协商一致解除。<br />\r\n" + 
        		"8.2.2.&nbsp;&emsp;任何一方发生违约行为并在守约方向其发出书面通知之日起15日内不予履行合同的，<br />\r\n" + 
        		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;累计发生2次或以上违约行为的，守约方有权单方面通知解除本协议。<br />\r\n" + 
        		"8.2.3.&nbsp;&emsp;因法律规定的不可抗力造成本协议无法继续履行的。<br />\r\n" + 
        		"8.3. &nbsp;&nbsp;&nbsp;.&emsp;提出解除协议的一方，应当以书面形式通知其他各方。<br />\r\n" + 
        		"8.4.&nbsp;&nbsp;&nbsp;&emsp;本协议解除后，不影响守约方要求违约方支付违约金并赔偿损失的权利。<br />\r\n" + 
        		"8.5.&nbsp;&nbsp;&nbsp;&emsp;除本协议另有约定外，非经本协议各方协商一致并达成书面协议，任何一方不得转让其在<br />\r\n" + 
        		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;本协议或本协议下的全部或部分权利和义务。<br />\r\n" + 
        		"8.6.&nbsp;&nbsp;&nbsp;&emsp;如果一方出借资产的继承或赠与等权利变更需要对方协助办理的，必须由主张权利的继承<br />\r\n" + 
        		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;人或受赠人等相关人员向对方出示经国家权威机关（公证处或使领馆）公证认证的<br />\r\n" + 
        		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;继承或赠与等权利归属证明文件，对方确认后方予协助办理。由此产生的相关税<br />\r\n" + 
        		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;费，由主张权利方负责向相关机关申报、缴纳。<br />\r\n" + 
        		"<br />\r\n" + 
        		"<span style=\"font-weight:bold ;font-size:16px\">九、&emsp;争议解决</span><br />\r\n" + 
        		"9.1.&nbsp;&nbsp;&nbsp;&emsp;本协议的效力、解释以及履行适应中华人民共和国的法律。<br />\r\n" + 
        		"9.2.&nbsp;&nbsp;&nbsp;&emsp;本协议各方因本协议履行发生争议的，均应首先通过友好协商的方式解决，协商不成的，<br />\r\n" + 
        		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;任何一方均可把争议提交丙方实际经营所在人民法院暨苏州市高新区人民法院诉讼<br />\r\n" + 
        		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;管辖。<br />\r\n" + 
        		"<br />\r\n" + 
        		"<span style=\"font-weight:bold;font-size:16px\">十、&emsp;附则</span><br />\r\n" + 
        		"<br />\r\n" + 
        		"10.1.&nbsp;&emsp;本协议附件作为本协议的有效组成部分，与本协议效力一致。<br />\r\n" + 
        		"10.2.&nbsp;&emsp;本协议的电子件、传真件、复印件、扫描件等经双方确认的有效副本的效力于本协议原<br />\r\n" + 
        		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;件效力一致。<br />\r\n" + 
        		"10.3.&nbsp;&emsp;双方确认，本协议的签署、生效和履行以不违反中国的法律法规为前提，如果本协议中<br />\r\n" + 
        		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;的任何一条或多条违反适用的法律法规，则该条将被视为无效，但该无效条款并<br />\r\n" + 
        		"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;不影响本协议其他条款的效力。<br />\r\n" + 
        		"<br />\r\n" + 
        		"<span style=\"font-weight:bold;font-size:16px\">以下无正文<br />\r\n" + 
        		"请在本业进行签署，并确认已经清楚知晓并了解本合同的所有相关内容。</span><br />\r\n" + 
        		"<br />\r\n" + 
        		"甲方(资金出借方)：<br />\r\n" + 
        		"身份证号：<br />\r\n" + 
        		"<br />\r\n" + 
        		"乙方（资金借入方）：<br />\r\n" + 
        		"身份证号：__________________________________<br />\r\n" + 
        		"<br />\r\n" + 
        		"丙方（服务提供商）：湖北天诚信息服务有限公司 盖章：<br />\r\n" + 
        		"日期：<br />\r\n" + 
        		"<br />\r\n" + 
        		"<br />\r\n" + 
        		"<br />\r\n" + 
        		"<br />\r\n" + 
        		"<br />\r\n" + 
        		"<br />\r\n" + 
        		"<br />\r\n" + 
        		"<br />\r\n" + 
        		"<br />\r\n" + 
        		"<br />\r\n" + 
        		"<br />\r\n" + 
        		"<br />\r\n" + 
        		"<br />\r\n" + 
        		"<br />\r\n" + 
        		"<!--end\r\n" + 
        		"\r\n" + 
        		"             --></p>\r\n" + 
        		"\r\n" + 
        		"<p>&nbsp;</p>\r\n" + 
        		"\r\n" + 
        		"<table width=\"800\">\r\n" + 
        		"	<thead>\r\n" + 
        		"		<tr align=\"center\">\r\n" + 
        		"			<td colspan=\"3\"><span style=\"font-size:16px;font-weight: bold\">湖北天诚信息服务有限公司</span></td>\r\n" + 
        		"		</tr>\r\n" + 
        		"		<tr align=\"center\">\r\n" + 
        		"			<td colspan=\"3\"><span style=\"font-size:16px;font-weight: bold\">擦 协议附件：还款明细说明</span></td>\r\n" + 
        		"		</tr>\r\n" + 
        		"	</thead>\r\n" + 
        		"	<tbody>\r\n" + 
        		"		<tr align=\"center\">\r\n" + 
        		"			<td colspan=\"3\">&nbsp;</td>\r\n" + 
        		"		</tr>\r\n" + 
        		"		<tr align=\"left\">\r\n" + 
        		"			<td>放款日： 2017-12-03</td>\r\n" + 
        		"			<td>起始日: 2017-12-03</td>\r\n" + 
        		"			<td>终止日：2018-02-22</td>\r\n" + 
        		"		</tr>\r\n" + 
        		"		<tr align=\"left\">\r\n" + 
        		"			<td>借款合同总额： 10000</td>\r\n" + 
        		"			<td>借款期数： 1</td>\r\n" + 
        		"			<td>期还款额： 300</td>\r\n" + 
        		"		</tr>\r\n" + 
        		"		<tr align=\"left\">\r\n" + 
        		"			<td>平台管理服务费总额： 260</td>\r\n" + 
        		"			<td>业务员名字： 湖北天诚信息服务有限公司</td>\r\n" + 
        		"			<td>&nbsp;</td>\r\n" + 
        		"		</tr>\r\n" + 
        		"		<tr align=\"left\">\r\n" + 
        		"			<td>放款卡号： 123</td>\r\n" + 
        		"			<td>放款银行： 123</td>\r\n" + 
        		"			<td>&nbsp;</td>\r\n" + 
        		"		</tr>\r\n" + 
        		"	</tbody>\r\n" + 
        		"</table>\r\n" + 
        		"\r\n" + 
        		"<div style=\"margin-left: 40px;margin-top: 10px;\">\r\n" + 
        		"<table border=\"1\" cellpadding=\"1\" cellspacing=\"1\" class=\"table table-bordered comtable thcenter\" width=\"700\">\r\n" + 
        		"	<thead>\r\n" + 
        		"		<tr align=\"center\">\r\n" + 
        		"			<td>费用明细</td>\r\n" + 
        		"			<td>金额</td>\r\n" + 
        		"			<td>收费日期</td>\r\n" + 
        		"			<td>总计</td>\r\n" + 
        		"		</tr>\r\n" + 
        		"	</thead>\r\n" + 
        		"	<tbody>\r\n" + 
        		"		<tr align=\"center\">\r\n" + 
        		"			<td>管理费用</td>\r\n" + 
        		"			<td>10300</td>\r\n" + 
        		"			<td>2017-12-03</td>\r\n" + 
        		"			<td rowspan=\"3\">21600</td>\r\n" + 
        		"		</tr>\r\n" + 
        		"		<tr align=\"center\">\r\n" + 
        		"			<td>附加费用</td>\r\n" + 
        		"			<td>1000</td>\r\n" + 
        		"			<td>2017-12-03</td>\r\n" + 
        		"		</tr>\r\n" + 
        		"		<tr align=\"center\">\r\n" + 
        		"			<td>第一期还款</td>\r\n" + 
        		"			<td>10300</td>\r\n" + 
        		"			<td>2017-12-03</td>\r\n" + 
        		"		</tr>\r\n" + 
        		"	</tbody>\r\n" + 
        		"</table>\r\n" + 
        		"</div>\r\n" + 
        		"\r\n" + 
        		"<div style=\"margin-left: 40px;margin-top: 60px;\">\r\n" + 
        		"<table border=\"1\" cellpadding=\"1\" cellspacing=\"1\" class=\"table table-bordered comtable thcenter\" width=\"700\">\r\n" + 
        		"	<thead>\r\n" + 
        		"		<tr align=\"center\">\r\n" + 
        		"			<td>期数</td>\r\n" + 
        		"			<td>日期</td>\r\n" + 
        		"			<td>期还本金</td>\r\n" + 
        		"			<td>期服务费</td>\r\n" + 
        		"			<td>期还利息</td>\r\n" + 
        		"			<td>期还款总额</td>\r\n" + 
        		"			<td>退还服务费后结清余额</td>\r\n" + 
        		"			<td>提前结清应退服务费</td>\r\n" + 
        		"		</tr>\r\n" + 
        		"	</thead>\r\n" + 
        		"	<tbody>\r\n" + 
        		"		<tr align=\"center\">\r\n" + 
        		"			<td>2</td>\r\n" + 
        		"			<td>2017-12-04</td>\r\n" + 
        		"			<td>10000</td>\r\n" + 
        		"			<td>260</td>\r\n" + 
        		"			<td>40</td>\r\n" + 
        		"			<td>10300</td>\r\n" + 
        		"			<td>0</td>\r\n" + 
        		"			<td>1080</td>\r\n" + 
        		"		</tr>\r\n" + 
        		"		<tr align=\"center\">\r\n" + 
        		"			<td>3</td>\r\n" + 
        		"			<td>2017-12-14</td>\r\n" + 
        		"			<td>10000</td>\r\n" + 
        		"			<td>260</td>\r\n" + 
        		"			<td>40</td>\r\n" + 
        		"			<td>10300</td>\r\n" + 
        		"			<td>0</td>\r\n" + 
        		"			<td>820</td>\r\n" + 
        		"		</tr>\r\n" + 
        		"		<tr align=\"center\">\r\n" + 
        		"			<td>4</td>\r\n" + 
        		"			<td>2017-12-24</td>\r\n" + 
        		"			<td>10000</td>\r\n" + 
        		"			<td>260</td>\r\n" + 
        		"			<td>40</td>\r\n" + 
        		"			<td>10300</td>\r\n" + 
        		"			<td>0</td>\r\n" + 
        		"			<td>560</td>\r\n" + 
        		"		</tr>\r\n" + 
        		"		<tr align=\"center\">\r\n" + 
        		"			<td>5</td>\r\n" + 
        		"			<td>2018-01-03</td>\r\n" + 
        		"			<td>10000</td>\r\n" + 
        		"			<td>260</td>\r\n" + 
        		"			<td>40</td>\r\n" + 
        		"			<td>10300</td>\r\n" + 
        		"			<td>0</td>\r\n" + 
        		"			<td>300</td>\r\n" + 
        		"		</tr>\r\n" + 
        		"		<tr align=\"center\">\r\n" + 
        		"			<td>6</td>\r\n" + 
        		"			<td>2018-01-13</td>\r\n" + 
        		"			<td>10000</td>\r\n" + 
        		"			<td>260</td>\r\n" + 
        		"			<td>40</td>\r\n" + 
        		"			<td>10300</td>\r\n" + 
        		"			<td>0</td>\r\n" + 
        		"			<td>40</td>\r\n" + 
        		"		</tr>\r\n" + 
        		"		<tr align=\"center\">\r\n" + 
        		"			<td>7</td>\r\n" + 
        		"			<td>2018-01-23</td>\r\n" + 
        		"			<td>10000</td>\r\n" + 
        		"			<td>260</td>\r\n" + 
        		"			<td>40</td>\r\n" + 
        		"			<td>10300</td>\r\n" + 
        		"			<td>0</td>\r\n" + 
        		"			<td>780</td>\r\n" + 
        		"		</tr>\r\n" + 
        		"		<tr align=\"center\">\r\n" + 
        		"			<td>8</td>\r\n" + 
        		"			<td>2018-02-02</td>\r\n" + 
        		"			<td>10000</td>\r\n" + 
        		"			<td>260</td>\r\n" + 
        		"			<td>40</td>\r\n" + 
        		"			<td>10300</td>\r\n" + 
        		"			<td>0</td>\r\n" + 
        		"			<td>520</td>\r\n" + 
        		"		</tr>\r\n" + 
        		"		<tr align=\"center\">\r\n" + 
        		"			<td>9</td>\r\n" + 
        		"			<td>2018-02-12</td>\r\n" + 
        		"			<td>10000</td>\r\n" + 
        		"			<td>260</td>\r\n" + 
        		"			<td>40</td>\r\n" + 
        		"			<td>10300</td>\r\n" + 
        		"			<td>0</td>\r\n" + 
        		"			<td>260</td>\r\n" + 
        		"		</tr>\r\n" + 
        		"		<tr align=\"center\">\r\n" + 
        		"			<td>10</td>\r\n" + 
        		"			<td>2018-02-22</td>\r\n" + 
        		"			<td>10000</td>\r\n" + 
        		"			<td>260</td>\r\n" + 
        		"			<td>40</td>\r\n" + 
        		"			<td>10300</td>\r\n" + 
        		"			<td>0</td>\r\n" + 
        		"			<td>0</td>\r\n" + 
        		"		</tr>\r\n" + 
        		"	</tbody>\r\n" + 
        		"</table>\r\n" + 
        		"</div>\r\n" + 
        		"";
        		
       
      
        // pdfHTML specific code
        ConverterProperties converterProperties = new ConverterProperties();
        try {
//        	List<IElement> convertToElements = HtmlConverter.convertToElements(html);
//        	System.out.println(convertToElements.toString());
        	  FontProvider fontProvider = new DefaultFontProvider();
        	    FontProgram fontProgram = FontProgramFactory.createFont(FONT);
        	    fontProvider.addFont(fontProgram);
        	    converterProperties.setFontProvider(fontProvider);
		HtmlConverter.convertToPdf(html, new FileOutputStream("C:/test.pdf"), converterProperties);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
