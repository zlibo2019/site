package com.weds.site.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.inspur.uploadAttendance.UploadAttendanceInfoServiceStub;
import com.inspur.uploadAttendance.UploadAttendanceInfoServiceStub.UploadAttdInfo;
import com.inspur.uploadAttendance.UploadAttendanceInfoServiceStub.UploadAttdInfoE;
import com.inspur.uploadAttendance.UploadAttendanceInfoServiceStub.UploadAttdInfoResponse;
import com.inspur.uploadAttendance.UploadAttendanceInfoServiceStub.UploadAttdInfoResponseE;
import com.inspur.uploadPerson.UploadPersonDetailsServiceStub;
import com.inspur.uploadPerson.UploadPersonDetailsServiceStub.UploadPerson;
import com.inspur.uploadPerson.UploadPersonDetailsServiceStub.UploadPersonE;
import com.inspur.uploadPerson.UploadPersonDetailsServiceStub.UploadPersonResponse;
import com.inspur.uploadPerson.UploadPersonDetailsServiceStub.UploadPersonResponseE;
import com.weds.site.entity.DtProjectRsp;
import com.weds.site.entity.HaiyangRecordListEntity;
import com.weds.site.entity.HaiyangWorkerListEntity;
import com.weds.site.mapper.HaiyangMapper;
import com.weds.site.util.TokenAES;
import com.weds.site.util.ZipUtils;

@Service
public class HaiyangService {

	@Autowired
	HaiyangMapper haiyangMapper;

	@Value("${imgpath}")
	private String imgpath;

	public void uploadPersonDetails() {// 2-工人信息 0企业1项目2人员3参建单位4培训5班组 throws
										// Exception
		try {
			/// 循环海阳的所有项目
			List<DtProjectRsp> prolist = haiyangMapper.getProjectList();
			for (Iterator iterator = prolist.iterator(); iterator.hasNext();) {
				DtProjectRsp dtProjectRsp = (DtProjectRsp) iterator.next();
				/// 根据项目编号取企业下所有的工人 每次取50条 top50 取新增的
				String xmldata = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" + "<data>" + "<List>";
				List<HaiyangWorkerListEntity> userList = haiyangMapper.getWorkerListByRegserial(String.valueOf(0),
						String.valueOf(dtProjectRsp.getRegSerial()));
				for (Iterator iterator2 = userList.iterator(); iterator2.hasNext();) {
					HaiyangWorkerListEntity haiyangWorkerListEntity = (HaiyangWorkerListEntity) iterator2.next();
					System.out.println(
							"imgpaht:==" + imgpath + "" + haiyangWorkerListEntity.getImage().replace("..", ""));
					String staffdata = "<Staff>" + "<mig_name>" + haiyangWorkerListEntity.getMigName() + "</mig_name>"
							+ "<idcard>" + haiyangWorkerListEntity.getIdCard() + "</idcard>"
							+ "<image>data:image/jpeg;base64,"
							+ ZipUtils.img2Base64(imgpath + "" + haiyangWorkerListEntity.getImage().replace("..", ""))
							+ "</image>\r\n" + "<home_address>"
							// + "<image>data:image/jpeg;base64," +
							// ZipUtils.img2Base64("D:\\源码-中建对接\\wwwroot\\photo\\20000\\20000541.jpg")
							// + "</image>" + "<home_address>"
							+ haiyangWorkerListEntity.getHomeAddress() + "</home_address>" + "<national>"
							+ haiyangWorkerListEntity.getNation() + "</national>" + "<tel>"
							+ haiyangWorkerListEntity.getTel() + "</tel>" + "<work_type>"
							+ haiyangWorkerListEntity.getWorkType() + "</work_type>" + "<educ_level>"
							+ haiyangWorkerListEntity.getEducLevel() + "</educ_level>" + "<plt_type>"
							+ haiyangWorkerListEntity.getPltType() + "</plt_type>" + "<native_place>"
							+ haiyangWorkerListEntity.getNativePlace() + "</native_place>" + "<grantorg>"
							+ haiyangWorkerListEntity.getGrantorg() + "</grantorg>" + "<acct_num>"
							+ haiyangWorkerListEntity.getAcctNum() + "</acct_num>" + "<work_team>"
							+ haiyangWorkerListEntity.getWorkTeam() + "</work_team>" + "<isTeamleader>"
							+ haiyangWorkerListEntity.getIsTeamLeader() + "</isTeamleader>" + "<work_role>"
							+ haiyangWorkerListEntity.getWorkRole() + "</work_role>" + "<corp_id>"
							+ haiyangWorkerListEntity.getCorpId() + "</corp_id>" + "<own_corp>"
							+ haiyangWorkerListEntity.getOwnCorp() + "</own_corp>" + "<status>"
							+ haiyangWorkerListEntity.getStatus() + "</status>" + "<contract_labor>"
							+ haiyangWorkerListEntity.getContractLabor() + "</contract_labor>" + "<entry_time>"
							+ haiyangWorkerListEntity.getEntryTime() + "</entry_time>" + "<exit_time>"
							+ haiyangWorkerListEntity.getExitTime() + "</exit_time>" + "</Staff>";
					xmldata += staffdata;
				}
				xmldata += "</List>" + "</data>";
				System.out.println(xmldata);
				if (userList.size() > 0) {
					// UploadPersonDetailsService upds = new
					// UploadPersonDetailsService();
					// UploadPersonDelegate soap=upds.getUploadPersonDetails();
					UploadPersonDetailsServiceStub upds = new UploadPersonDetailsServiceStub();
					UploadPerson up = new UploadPerson();
					up.setToken(dtProjectRsp.getToken());
					up.setData(TokenAES.encrypt(TokenAES.gzip(xmldata), dtProjectRsp.getToken()));
					// up.setToken("41a10457");
					// up.setData("HdKS4skBdVRflHPsPy0vAOfaa4jVLcvjBRlmOsXZ8/y0++6Q0K8o7MJVsbYNljhcFmqO39uAWWwdcMjb+6ihAdP9gzbHFzTUYBr1+r3zVIvRIjZK6T1VHtS0pOAswA2cwE8vnumGx642VUH/gklC0zPwdi5XOwavu6FJYQfx2LGiBFUncW/DPSxY0kqDykT/7ONoz3OGe6Huk0lUnYduk4hbpzDaywJHa3LIslg1rIGzbz+j1njFwZl9CKUtaHwP+hgL4N8syfLrgHtELV/Cvl1qZYWNN8msAKC7uMamipbrd229Z6G4jCBli9I3C24GKbGPD2S5QQHTL+iK41/0+raDSGcU91c2NgrrAJ1ALyc7nTVIJ5NGe+DuxHnABW8KcoYxSvNkEiJ563MQalajkO067ml0LmhZ8GWbKQpIQ88Zrx2FEwzm5wvRcgoJ0j/8hWnmZUZQGA+kl9HVzEYUxnkBDTznKvwza7oARRD2m8nURhbmyym6fQU6qwwj1gI5PIpw72UgdJJCp17LK75zOgy2D1GsM9ncKw3sl6NF/qvI/LCF9F0XkWHctyj6J6ptxtkxYFGueDvdIzKHJGbGQoOPSjb9iq8earjy5Etcr9x7SJuzRp+kY8AyckmdBxHS6e0xQF1I/gfLfuzhnDDIPhgoqSBZmzy++uic6RYc1njv5GwGgCHdTMWWE/QR5HJzqnAIvZywKIw0R1iRaAXqwwLQy8DKbhTYKTqLSu0iXwX/trZTbGxbypHZOyB2lG2mVdhO6gX75Jgt45/Yu7QuHIvhzezQ8/qX2uc+yMC72CgQkwRb3aJ899urEH9EhfesyK7HbsedubSvY5YS/oZ3YJLuaZ0CUUAmdEq84OidgmmTjqf4rKRsNrELeL/ALIFXZ9chZ5QG9ApjqIWKXLI6X3pR6ppQYQD2SDArrIQTEYLGX74ofanobfstbOifNWRggnZmqSblmFlxzJiXbbDiYx4gWaAAu12ziVDXtdEJEXiZ2/SgCDwVfnNq5ebKZXS1MlOgt+iqyWfjzlL32gYCQbeeguC2MMpQjqyQMJri6SndsXqIP2z9OLKFFPQfTq6+QNXyRyFiLBc7moMcYfP/cMh/XjLIONhSxZkB6v5/SFpT3PnJdQBigUNJDkpSNdmx8W/TGwB8ae32IZVPQkTPEHJU0c6dpGNnOgM5WnGO8w9NrSq3SUfmphRD13nMDPRocFi0RhGPznGD7JGN/Ij7rsViDl7VsCDIdC/rJonNTIFDpKlNxVAEx0oOTLwYownRauFM9nid3qxP+PnCCSW5St0SBItAiix6IYjs/d9xLdklr0boqToKI2gbuBXewuzrMnotb4Mozs0fjW6yNrI9lJxFLd4zzECIkXS+S5+2QYupnn8Nd4UlZiOe7T5SDReM01nu8GQa2/GsyLckMyMkV1V2MTdYcPhbiIQu3GaFJPJZaS0+2mT0cM33jeH4cVaFwZIbERvlEsj+tDGhqaAEzsd5Fnzjgv1gn2DN3B3FqNfsjmFrl1hfr+tO7+uc+LFwXvk9XGYSOOXKYr0w/jgMnlzYHmeiqJ5bsMh1KbFy2NDZ69LK899xXk9gH4RNPG4/aGSsEJ334UfpMDJKjT3XQ7dY1cLCur5tABXR8NTElzKFr2Stom7SLEacpFDcyNLVCK1F50L81F3TVKcr5ZvmXzG315H+5wJJ1mpPK4S1+IBjXw2H9EOuDNKiSqUXOEKsOMjm2ZkIZAhi6VWUQRqv/g8u2olS/1YJHqJeKNiF+OYVquAziyKGHBupUlCx1Hmpv9xkI8eyUxFvaM/WEYWoArJo7TZhF0xq9X/JpxIysDXmpUlm+Fi1ArgO/plPfcJf4Pm+1jWeX6Q496Mrv6RvINS5KW+QL9i0YUVUpsXbZTfkXRwhcZm8zM6kG4rcXnnxQ1VRoa/ImWViNL1UeZr+iQCzeKMB4BLUDIldVHUiWQBz4xWUE0J7fdNTT1B2zn4gQCE2ZPlaN6BWtorYBi+DRMlmBTKcUF02+q5VIU7SL1nfCEEQYB9b1JLqVEAt8vKUXmeiYr/tdsEABKFvgrLyYXM062gqboD7ZUAFYs7kTStky2oiiLORyUyYyHUDwOr477gcCEdln+U5/ZcAmWg/EBXFUEY5cTzk61ZCgz7WtnwOLVb2//dyrwjLCN/eQRBSJZiiHXgq9/uKeCnCvIGaOtmcjKEqeet5Ski9f0uKTv9I1dje8e2DYO16BIEPtIX312BDjFrlxYY4I0J3UD2Lwu938JnnH3JqFvZdSXMsS046pW1VNMIf82/3fOn5p9iQRdwMMLZFG4355s7o3NTG9avz/gJP/LLHFYp5OVqH5CZ39X5ZSX4HMH/MHq+XrpaqjPoTW+b2cziAbX5H+Ol0krZivgRPelW+4Z1VbwE4eJrj3mK1G5PYqO6PS/lheYqPSLDGieCn5WZEWH8BOc572iwxu+/DC5GPwU2dbgYUOFphyxnkd1h7BcuP5Ig3EGrr/foRoYm8jpG4rRk4PMyge8lAMJ0kIrZ0M9UGYhRF/6Rk6AwBqTXa96Wb6FCTyWsjtuIGTvPhhkCpZvOzqzTkUUHvdZNKyZFXxLoyACdXirnfv7IbE6kCScUo/uslnJ6EyumWaBIQtqmEyEU1hbdZQtOcPR/n4q15jZQupqO8Blfy6zaY5Eba6EsplS5m2Sp6Y4xV31abDVBlgQ2TG7FF4ONy8M6J/0d4symsZsJOylkEV/bqTR24ikTXTm5OguHyvcCBX925HN+2ERi/JTNwUHieoti3F5AHlIHSDUwg3url/jAWwD6OUgBrzYver2Y6yaAEYRGKwFt6XvRFcJLh8JKTTMpChmhcCyK5diwamhRwE67XyrKsDkdLs0zhTpMo9byudNLwkiK7SH6azI4Kd787+Hn8mKmsRppsjPlFsWR3/ZythLKMJdTRAzV/dAUunpe2QUITEgdRTtI/xT9o/Id94Xpj0PbPj+xHgBi+sC9KDX0jZioKiXFywALT75xmyUhUQozwLeJFW2Y04MdBnhHLsEaBVPm6r0ZkqH6dmeKFfuNfa8mdz35/X/dBVvEovdvgu/5WOdvG+ec75pA8nXMbNAYybdF1xO/RiCXSOAPrZo2pt+2p+G8FKS3UzLrYhISgTOKS8coIX94Kd5mOhi3FFNdEyEOci65LJnKs51cEbvODw3/xg/bnv+zbjGWOSfY9xtDRbKB2i1ITUNQQQ2gLV/O7r7plF+Jcn2BzPpgQ4QGjVWBR20J4EGkqcPKjoyuN7GhywJf5yPQPyln5SySZGqdo5eb/wWeiHwKEUw02+D68ydTvNsBGrGNDTqySIoCL1A33qPeomwxUboTcNe2nNl/OvPCo9pYJ1YS3JJiJfr2gdHh6VRiECcicvzPg5dnt/0E5XCJwOhAE8l0qusHYaRMZ1yNLXi4A1WGfknNKFYcMJFYEVPPx7Y1JLgETQ1uSu2eoG4fMbPveNh1iBp9Jgjl5mPrStRmZojadWuTJLmZu3wi5et6Ra9Swoh0QxvAg22TEB8Lwf80ez7/4DX0xu7E3XMprvYVldakgo823wC6DWbqI8lO8XC4ZbijU+RLnnLhoL7CE4+uyFCKwU/DKfVcGpwrog135SDnNhAT5eGHTj4T7yAFPER3KJh1j2dLToec/7HdV7BXnvVDgaUJe86DVgJ1IIR5EtYrlKJt+n+5YGQ820hTFcyBX6sAPUqEbkjN8wfpklmUCj9i1Ks3WNLODQVxrt094QUiyzYuKym+epuuihcKxGpyWSA60FUiMOifDqx/nR9835A3oeT+Z+eH+yhO4yC2Fg9/GFq65deilhfgR8ZMEiHXymhYEkLjm2EY0FwKLLNj0P4hr7itcHZ7A/7LMNXI1lNQJG2F5Ev8/UP1WBcuboKGOhodgiQu9pp1tM/VhFEiTbE0H+H154xE6VjNomozYZd3ZQwxogZ6KX6Jz7fWOZNtn+XjaaRx0mUDLfn1Eu96sBXwGzQ/x9LKK5VMNnHD6+Ialg5dSIsBfGeCeTUeSSSNTagrFYRjRUREGzB11i0mv7P4xsinLuPotFJWUyfXpjp/nKcwrqNbVmMDLOmGJstgzuKp208kQA5LBPFm9rYH1VDX5tOPuUr5VFyE9wN2mGMoVjCioh+22AyV4WxuS1I+jBclPVRmN5ZIBbSviuqqwxZKUHDle8rXVjsaza58bbRoZkT0gtmOdupP5iH7+NYHr5TVFLqEO2JOexODGhI0JJ/TEb4ml/g5jzzsILH5yR46vWHvRi/YzkGJvGjMQlA1u0VpgUnHEjk2dJ1hgXTYT9SHvl+yyheFiR12UkbWr8HWTZoxqjNX7Fvf0J2DsutSTYWUOpD1v8lQS6rpWzJKLoQJPSgStz2Je6S/UdWmPZq9q5FWzQp9XLz27kjuUlsmaEQ9OvUZ9tqFCgDL69HDLGsWxmbCFmeb06OUy5l5zS9LI7ZrOPGfTpHBCCBHb6n055ScCiVXaR7TXTSazHNAMVpaX94+IU65IlWyCKPQ7WXHg8FiQlfivl3EI52rtmSnjxq4d0A8ctvAFXYpyz6AbrlxXoS4oe0/DkNBc1z7nJ6TBe1ydg6if8VMGctxy8a0scmac6lBQrJtqgaLYZa+JCgkrdkVQuOgbvgvlMwqR3GJ8LeBEml+oqDLnCx9EmdzGbNHAp+2Ak8riDuZMHGTCWEEdxUGlUce3Q62JwrY9leIpOxOqNkbUbQpvJvOFYQU1fXynPz3NZj+BsWmtYV54vxvJTvjpCKq5/xU0nDlWFFkcWlSeh2bNnrQZEMrRWjj6KIw5qclWkZWQDITQwFz393+/5bpbLwWPHWW/tsJcSqmQNoXHfiCyTLWfY+4BOEu0i5vPIzn8nOZcM1otzbGPtJVhf0X3qRNuhZTStULK4C6nbni+UmWaA3LMfPg8UQU86Xx/+WXLtOQvrV013KHQdlf24Q6O6b8tJ47PfZxvXFYGSuDWMbLn5bwhKABE+bHspjGq9QbBtVZ/qnrICV8x9vFCGNiIV9jrsd3M4PuhQPO5aGoyrKSO0pYwBTsC5kukhKyCdFV9AqfhvLuQ03vp9nfyUGP5YWa5euSjE+lVldO5BeLsoRqyF95a7jv3LEpSUlRHF/HVHzjkupClfrziNk7KCMcfrrI5FPiweZ9KzXoiJhIEvEon7F2bnsoqH/cgYmPgd72t885ltek9vEgtdp9D0b1hPi60OnZgyF2oDlt+dZmwDPnxrdtQd5yq7qdFD31bG5pXpY0gFn+ZOU8uCtvYI5Reg6Un7mdP1NQKhczoxuaqylf0vFlA0t+BpgzwDEglR5HEkhowK0nStse85oKnrljWInUZsVnb5F8HA2o8p8EqNkzc3P8D3rRZhAEWnDD5cBAC+rYFGqn373r4+uxeHd1ggvIu3JL/eFaN/UXBcCzeg4+na0zY+aq4JZjTCQOgvkV86dve0+oI9dQ1i8dszLy1L8qj7+jq25g4mC1XHFxK1HYpWI4N1+S7x05Y18s6R5zuRCDd56Ch6ddjNoV9+seyM6w5+HVDaU4OCz/ppG1akAA9A8hkSTgK/UoLr/hjWxryTYNl4dkpqPoBVDI9ODTOo2R3XQU4SqZjvbjohp5StvBjNedZFRVAWgPyoHGtqy+u8sTsqlB2r6DSN4RD8aeGiL5WXhCsYssu+Y1octhPd0jnwN2e0/v94WXN4By1LqKZE9ymPUncEzqnP02rOxpUTiOjYA+9ofIIUfHRgyMweHywH826bgfGcy2yZRFnFMUr2RD4amyRAudFKAnuO9As3j0Vll4jOhoMPFZ+eh9V3ot8Ep6Br/Jzbqp26QFfu1NMjrKuZ6pIwv00PuhBsWX/0FaZn4WB+fh4YKc2VChmnEapXwUQW6y570bW9NXALRy7behwTWucPyHSyhvOyWrN8CyyweTFJiqFvpBh5m7DKqFhQ4EmiHPRxjh5mkGnAwFx/8iMEAliVIJ+EZ8I2KDTCElV6xFiUxdPhEIAfEUqA1xk/dUJK3ZIIcGmqmzprq/uhbfNhxzBHCweU7vnLSOxz0H+96RCqHvu1Fp9tRAeIPHEkYkJ79rEQhUF8lJQbO43HiHn7mhr0y7STKLJ18pySw8GrjtBJpVlzavWuTx+hrgsCO1WHW9laGf81hVc7FIVuv/GKTu0INl/9g7rbfRveU8XyiHCYvRVPX4v1vplt0cwraJSU+BXtOCCTs8NEIb/HfDk9antOMGq4mFXdSmwblZDPojCEBQIGgoKmhcBNtl1tfxwcTMt6pXwjkEq3v/q0GJ90f+RegrpK2OzVqTz/iH3lDNU6D+pU1fBung9ppmNew5+qZQzQLKR4Jmhbc6ISHdMyGzlKVcs9y0FjRIwoFfPlO4BnZ84HcxEkOtOdar9u2vMdiiFQUI+NScwxg5vqEnKBxXJ9syLRn5f7TUcbNoJc1mMfwzkZM5+KZkZ7Mwp4/XQNAnzh4qlE1rrG95d/mgKaL+MJuuVVR+0b77nhxsUl2iAw+VWeTL4av9ruI2zfwR0u6TUx9+CmIKy07kA6ZmJ0Amvcm3GFVqDZEzKpfijxvlKK3xNxgOO8FeyOym4U9n+QIk96dOjrRmft8qBwu0E5ychjBg15JpSKxQE9MlRfhxtgJ/cRAb23l2OzKsyGdQDMcPOGwPjM9uZB8WXiq9MBeu2UpdqAdwqAg31rjAPbTJBWyFj09rD/3rcjPgOyGyyjWW5lqjRxvff71HHnf2V6fdIJRG+SqIdLJQYmyQfKh9JzJxmyaGy8coN3iHuke/AftQJDkWPnh1UAxiz6sF+dXVpBoets99qXCDu2X/QZHqg4zkiDP/H+wiLuBWWa4jS6VifbCuYJReFAHVZndLCuJYFlzNM7Dutl+/1IGldFwaetLHDuBMFbe0z8W7OLqNvLZJ759ohYlqOulxE1T/DJawZKs4P522zxL9A+TsQUN7CzCVNArYTi78Z8NpgDljf7SZA1VXgg+mZNiR0uHtri0IZwqnP7uZsZ1AYysOxK2FEDF15aG92szsut7YfMSqO4zGxrKKZMyqsPG2adULM4HyruHvA6cRZsDYY9qiHXefOs3HASuv2zPMXHGywGqXOFphfYYFwzb6W1uHaFTYBN45tshiU73EEX48bdnvNkRDLUu9I/zop0nP5kIF+++ReD0WV1OF14JTBst/oCmUcWP4Ux6s3pADaImkNS6YJuyWnAfMndgwtuZgSzgqKncECyL/uxgCjqd5jAwvC9Q6ylQEYTRT9VosD54WFSG6Sp81ocSEpHdli7/GYi2UR23vJSsgZxP67LdsFBfYWe2qOiLdFSHezKEYg08eYtrtSQIXboPJfcmCOZ+77SXMsD5gfoGbEI3fulXG0+hNU2wcuWSb9i8Vx/eCwtrUNKbHe4Mr5lKUb90IZtTolici9aiMCyHhlikS2/V9o8GVsLpJrrEKdgVKOyQ+bxdn7CTL/Aufs2dwaFWNqxro4zsl0uhYpzUd7r9h1ioLVD0HSYhMWOuLx7E3OZ8jSFgHZe4Crc1FZSVJjPBXS90Dg9xQ1r5gPUWo0vAgAQHdftcf9TjUSxghVS5qFI9lkiwuDBiUSXZpRgNPeExcwCqI2eZqTpVzFwQ01R5MBQh1g0KQa0oSWFDrektDcRJRK8yqaRKpETDlLvZRMBbUyrsM6RHqos3Ak1tvS8J2V7tTYZy1dHsW5fWPixMUi4jjm9qQZq//V+QtoVWUXq6PPV9T5wlZ7B8rHtbFDDy1KxDjOqoHZIoWa5e9q6xQH9i5sIjDqDgdw4wtqJ17EJ9IFcEN87yjAAgzDKTslwUT+P41QPg7YcUe3WKHb2MWT2tDo/NVhUGtze6yvhlPj2w0s2cQx3965ipTuNSXCQ6HaeVwLjbEwgFUBgNRNNu/JcLIjHLSC+sAkzyl2gHelhbsA065My83eXSxNEmGkBjw8eyNEzbQ5K2a45hTF+z8ty9c9vOnZFWN/x81Yi2ISapiKL0KefCztANbIE18pjLFC/GYHcWguciWCuINiApi2oyZyNo6rkiWQI7tW7nxJtYu0TZ5TdcjsOmXDQSTOsZYFMPqdYWyPw05L+M1jsd8T1PILHra35f6RegONqDZA21643vN5vISvzZiWPkLh4kA4Rct6hE3izfAOt7EJ3zU7tzcg3C9mJDxj18BHUbmRInl4VpN39mou4yMMCjn0xNPeiSSZc1ILZd8LDsm4ik/PYoGWY6CV4wAeEYyfy0KlcaP2jPcrhbQ4cWzXHpNkOjHZzatm55iYZv0IXcLkXC915NgzQTlkxaQ6+RRZ690EVVQ/l9hYM2q9TYGNL+re2HPqJT1WSJ2vuUcUo/gjNqDm+yGv8uRJTS6o2vUA57UfCXjYPY+6psjHxOhvHzE4ToqnNeUMG9BGgX7C0muK/dtyBO1Fbcy5cUHFPOlXOsIDcYK7dja1mt8VWt99YnB2wZynl2JVpzR85UyEf8ClBVIXFMgHCBow2ABfnzBg1Rz6tf8doCm6aFaqJQUL7b/Jj5hbZRnXbW7OiMcL5B97QgietzgLQW5FJdz/6g2TBajzSD3p8bGU0UNVOLLjdcU17QiJ0g+LQOMfY2NyL1YFXMyUyFeANO6m9jOHzbzAJWGW8ZOKCfa+Rv53QT1rRHYktE8Wh27uK+1fBlOMQlEDdnl/S9xx+DHxy6E3NKtCT8leg7GE2CLvkmBbu6EPaSoAKNPAl0x1S/ct58r2oRzESd3guH2snvSXTcmWnoy9PGZ0+pAL2G56Bihr4uhl+Dn5THE5yfCsa7J8zI6lOX4iMxUKoRLSuL91ZVSx5VBBF57QgklXemsm4jhPn6hI8khaxXFe9sPCnWUmmZFbB7nEQEuGPg1ClpM2HXICV0UCZFAtEMeg14FnZG+BROEl9ctju+uC61kPRBQX+NlpLSQdFahthkee7vW3ON5NKtMQiWq98iJBVNoUXKzk/J+JJeBSHThiWAmPVn6VfPprjaOGTnmfyaszV8MHBLR2bWMuGkKyIsqHWmDtzcX/bB2awqouv8WDJ+hLlI85OSricUKmHZJXr1doMgjWeXRYcsA/D7ogn7MpnwlZzAgTUFn9u6ju684lZTAZMcY30QXaukKQngKERzSHGNRfVY0H/up2JDnjXHEDokbfH0c3WeC2K7pM02I5lXUNdWc/xiwsl4gfBNwYTHlAGqaI/XslKQvCjADPWcqaHp0vW3w0/9jGyvgT6z3o/HkrQIs44giQk4tQfQLdKtdYLGdKWwdTRcsjjIPX+FvPa57+tnWDn+Qyulb01bPkXOOwHg7AnOngIOvKm3/UUbp0zhYTe4i9as6VKx4jGWanyf0/YKsUhZaoY5pGS2wZzK0SmjxHf1rb29tkISaSO1NMUKPsr8zqY0vYrPGCfnm3nKr82MkEHRX/5yKBko36m5wZmDWH/OOfw+YcEDRwB8QJUzEkPg7sqcHJ+8RIfl9xEhXronKxHZ4SaCoWAEy0NprNQCEh+9AKpwRuZSmz6Yg7TKyT5YJotprLr2H3+ZNhLuVMKpJe+jHgWMR0TdGHkUlvQSdpG0W4TVR+gFvhCZuxs/FxYTfmeuXArnyTZpzcFtVa68xqhNWibtbNUTVsJ8vevfuXEAsmdYqPZ3sPL1ryykyY4+epvZLY+ue8CyJJyx0m8Pe5izhl5MdAbjfV2Gy7Y1avtqN6KpnJkvC2UIp8gJM4I62JWEp0zNcnsQGDJz6BPOC8aShkkKo7U/HaDuimVvQAM1SNAaw9kK0yJEXtGLO8zCw845EKiSlTG1wPmR9yoec3s7I0CZ7y09OqhuxE1IKmvvuukWBCjPC/rbUbFkP+7njA4Lt6SDI8GXkjNRc1UrHiGHf5rFaOl9cP5QkOy6KkoIHiP3v3doXZijwRBzcZ4VX14MVlwXqHjOZwf/0hjcRYrYt9N6JlDYqvJU1zUv60SX81K5HGIUR9Z8CyTaZa34DLQPZiq+S1YIMeTSPBLiqFoo9a1PHroqlRxPDb+KrnMGQTcFBkQVveQVG4NxKxK2eqzprxTXqhc89UY+NeMF7jaiR+5TqGRVHEB2c+N8/18SpoZCfARnsqU+BWj7SB5P1yRMTV09pXH8uRfCV940ow4BeM4a5wuwiIRCGSA9pquKNaHR/EhFprNoAT8B6+rqJnnyCc2ra+CkTZnqIX6VSmR+zXjoWztAZV4Q0LvsTe+t4VP4AoXoUQd5hsGEWSC1I1dHZllzXXlRHJXCZcIZx+er8Gj7krRlOSU45fFThEkes9TA78wSCbPJ1oBh8m3+IN5uwBkJCV68JEKd0Txq85b8tZ5zZ4KYBkMNuykuVG8qzt1fNQ6WeEpcF3OSBPtq8srQ1ZK21eP+FrSDz0BRljTnQJKIrYKyXFyO1ryKW3dPDkjxTXgSCrGvWVeVqTbhJxlOVDVfIJMcu8Xi3EGYimyDgM1JKduL6npOc9rrhbLFSvpLgyNy8ce0iT3ObARz3Li3YH+MHEqaEAxd0qe9LQDviKCJf13tEwVxMEDN+kBONWPhxwssB6p6uB0xNPKlWfet/5OY4AGh3DT9XaDxs+Wl79HXBUj2BhLXMMh7NKgS91596DnmSkK4FdvYu9uCEIIV86h1SDzBO5HWrmW3Lexl1HA9dPiDX1HWs/UpVXPCH+gWiIHvDJdNDG4jBORO0Wc7v5R+WWFRtRgYpd2K+oDnCtKwPYill8j5F8OLQE58jTYiSNKuQnPXnBOT2W3LqvQ1yRWyvwblg2bvwUDMzliy2cshXLMm4RGj5+OVe0AFEjcCCrYM/raH0Mc/3ssFT3avPS6QjO1VEaup4EPf05my7+G87H2VMOCLiw1QoPzCjXxl9BRBSDxPu4u+Je/cRqrzD3qblZVmIjF2uJeAjcYHoa1CeZ4xJU4rYcEo+XVhmfBR0zthQDhCkpteCHMOMsQN6NFn4Fl9pWUg2A56a3j83XKJQyZARKVAFEv9r1fLeaGgY8T/fkZAVzQ+w5yHV865odUoZgvrDsXCZ4yy/T7GbO3YaPCaZDgjIHYjiZ8pDL2Idq0tgvH3+705YiqgssN0zfENU6phOWc0XThjRPBSxOfNhKFHD2cMRt9lhllDrFrG+ww7eQtmfJYWqTgU0DVWlUqUOWX/BNye+JvBj3QUGKDz47E4z9TVSQA2SSeS7ZZ197GJ70qlJanIId6XrlvBVX3dco8XnoQbP/0YFFQTe8cJBrBYCwRVGjJi1B+tCbIY/Xqr74XJzjBBsrpwqmdZF3XDf2r9pm/qydu2phZ0eXWhi1oaBMOdVrVIcz4jXA/prpKxACAM2ISUD19d00ERXaojPKvIUFOYvzyWWkdD6x6IDuNAjq1t7+tEK26z0G+lu2B54ZbAZtucaGEnE5+oMtNIDdabQ3heiI+Ie19aW9WvfT2h1FtalSdD5s9C6mdqZcjZZHwFyAKtTFfy5c5qC5k2jpaxXsV2I2hdYeENlQt9syec39YOi0Sl10DugBrBPPFLORZAAi6jSamkr2B8wqYl9/NhJ0ROyFSIIhKlkKDTRYlB90QwiQWlibM9exkcTHRDKLdQTp4X2CZzHNHEIFdAsCvMroGRVagU7kf62QQ35x/nu0XDe7LoYMqPd8ZwezKqXja4HOQ9LAIcGezsA3ctWzKrilmxFptD/B0ov1Rtec6PVLhLntisN4AUqQWYvO15Uqf2Eb0b6IuYvYc3YFUksCxsM7MhnUtWsDdbF285YMb+cUE9gIuLWtdfmIYnZAgivYfzw2XhZBzymJ8QMNpjWAjxUOW4wbaId0XXUqEHElziD3QWJBEbDmvne1Uh9CuZPe8flalO1ci+9+rK3JXy4ABjgGULy86IikxpSbZrkj+kcYxbO0zpwO9RlPXxwDk+FYwpFsAe4Z/xTnoROC1rH7I8hpaRanhOZRvQMU+aB66J88kg4R59bbs/uJ2nod8/bpcCjowgv++KTuNqnvco3va8qEY/rAQB5L/hGzbWhcgSkLhcsM5tlxZo5SeoN9KID2mhPUpIyxMNYCefyk5NuyCDvZaORSxQR1ai400B0bSmEJ7kPEFZYcanjujVfTjKteULCzksFrJFrfBoP9yNPDuqFXAdiUQ8tYuAHaCdGMfnWRsZUFjiJA7pUY05UhWwzKrCHk8hFBxZRTbVukerCiFDMlIkXZczIrQhTeIMQcVx+Lu9w/lSIB9KliGfiS870pwp1M9TepDInIxvDoRtQkWXoYyJ1nj7S7UZuvZq7T2NAV4AGpYEk3+gWMNqdgxQ2mku/MQslGi1Jw4BlMBQO9mHPxzqc545R53aBWDqGq0lnnvaULsw8+/neqXjGg3E+cfkHGVCKjddOg8mx8qseh3+SYvKvWa9feZBTtBdH0iOf1GlvizMwI+fjc1LrT5Tg9XyFfz/i5MsCm3XnJ9fMx7F5qUqXYDPrABgGs/fz9JWrpvBAoTdG3eWa3gObpr3lLP/ralQZfH8HjumbSvr4Cle6Ze77JnGTuya4HWbV09JRtrFLE7vxMSPcz3c8ZxKfNo6131rIUj/3fsIWc8n6RS2nviT3FdsAcplzDPKcEYcrPKPnGR+5IFsX/SOkH3KiPLSvAIgWFT7EDhuVdWeolWi7Mdc1MHB7FvNbE+MX8STh+UjFGW5HjaV1nSNj0usikKobqYT47E40SPG6vjF3ty7bY0G1JJxKIxAk7XMjM/692/QBoOGKWK6HVgbfErGbqn4xhjvw1QFVb+Ynd9/Q5OuSCGgoKkpvlqPcota7KVyqlXT2e48g3uaHQygrbbcuH078AxGqwRfNq+O52o6Q9sa0T5q/TCycPwIgzGLDxFuhjnbURlaN0JnvjE7sHhyEC9eM5+Qrx/YoZScpDXmXyk5wm0x+m9XDYeuuWfPA4FRMBTnKAqVJerhaQhwXFdUIINEkelDSHGgDh7z+6deDbEVprWAgvkKqWFHav35Mwcj3VVpdt2MD1pDSV/NzIG1PtsX9SRKMwmxWqaWjv8H3ZNfjXuqVtYSfCsf1xsFKw/RYHe3akJfrjVyQdEyyFBdSj6L2XwODrwnjjwk32KjzHly+5vgpDrnoJETHgYUtwraILOF/YcYF+s+lwCNwOYjEH+YhjNnruQ/0VS6AL9AWOR1I+/kcnCZ66AUzjnC/RhYvuMQi4NMdjvoI3m6mUmBcnD8Oxiue3IwqUUhmt2ng/6lGkq8pnW3zIvR5BjJpVZNeTl+zKEdBLD78X0jTr+2PcYJnxLPg4oH6rUmUo+DbJNHwVYuTFZKB5DQCgxzchNViRTkJVAUeLsvd6M8ksFF/MpAPakmISImthrAm8JEsJDDdkZm/WaZtW5K1dEe3l+z1ZKSBHfjTU4XnyUBklJgS4chB0m3B/EjtfSSnj7sz7Lkb1vS1AL4WJOamTkiP7a/lBOCr3gN0dF4u+HBERVg4FFsZrIY/smzcTDT0L9MJfHCnSly+yYXiiJ7db5j+yHn1NtgIBLMIYpJ73oqVi8AXZZpKwSjn26/D3XPGyey5OuzhPHx/tvCNs80UoyRJiSoj2OQfTsFp1aJaCSHT/MHkHFYEhS7n+zH+KEinMvlrB1ErJNATAPzOph6Gqh8ndWJOW+Kx0aht2IN22b51cTbeu9mjux3AYVb8CXAXkJbg881FRqyebyRcLxJWLBEKW5I7SE0aUxZGrNt0Mf//TPaUg0R73C1ahSICZJKKhB78eI0DCBJOgXyaWL6Vgq+Ou1kWM/xDzPv1Swf513tkmtj9XCRx6RWHo2PE+dGz6xOmjnMHUDZc4Vq2WR9Uy51+Bv6+52op64YWAH0r7lAhznsALfskhENTXjmMnp/4MFVFlTKRBTpNplyofNhNEIvT0LvVXN/rjjlBoxAENVPI5KoaCUZutIvfLl8FnwKwFrsYb1FMCgdZZM1l8AvGO8osMPQQ93D1BlrLXSZgD+cCBuD6h6acbKJTkl3XwbSgCujQ978IfTPc9YwNS0SWbFpc/vaj6sDzLv3B7dJdjDnpHYnmMpb0o6JgDWWt7CdxR1J0efVDTCbhU7zxhOzTIStbVT1ZAgrdfUv9VJPUgcog0/EPGrhZAJgVnSBwvSNHWGqc4eSErBQs2uKHqfUDAsYnx5aKq7SaZnL/rhkqbMUqrn5ELljmtaT4LHEDLGB9Gm/5J7Pj6UX/VqCawvxvJ2Sxznuek61kXM3WgbQdGUeatf2PX5gbcLlIfJUaP32ou2j2Pivl/EwaPBY4R8feVvE++K15kXv6lqn93uY5249pzrXbKCD6oGORCXRHFVS2RhXpRXYiYWBhNasrKZyOa/wBXFVwF58m90ZFqVgOEU6LDhjRVpJFc/W7r/GP+6ZK9h1ZGNNswYKHkc+vhCFMIfdqv8I6AUU5Y2XY5Bg65BOeqXpzutuTUkHRyYqp8pCdZB2yMl0ZKWpJ1ayRs6JSJhyXup1mBkPgyG6OF/4v/wQ2mO7Oo9O0rFS5tPbIQ4z+TRwI12iS51LiPLlkGPKXpy8+kZIGkGet6pwMSuwGOH+jdvLMCBKix4ZZs++nWDXwMpiWJSu4UPN8XHbUbEHI/VxuAF+39xfLIDhwCOTDM/+XQ4vxt+JUuAr/ZV13r2IeaweTCiKl3UYYuxOLxWPlu5tFVUoPgvz3ZsV5hIFB5SQ3+C6gtcF7hW/qD4Fj1PSerPT+4SvE0G2ism0zx2ZyRjnqxBebtd5q5gZcMhhreu3nb//sYWbIYHwmFaAgA8kd2aBnzmWH6tFJyTRPLzAEo+SZol63qm1AfGs1D4Hqqt9yqAMTOE39LF/z8e+z5RNqczMs/rHes4QH75eHjOI2rMujggEfaQRyE2LeYbgoFifcS3azHm3PfZl3xW1GLiOytNNx+kQRe8TiRV59041gUqtSQMxmqozG726mnZ4pY+/uS1mKPaNeoyPd1YSPhZghMTnmXtoFqF52aDzC8+XOHHVELlbsYVzqn3gER/EcHVOdwuJA/vkkP26yBdtyGlF1s4EE8kF/+Wkh/eweUhf7PoRlr/M8vJzu/JTuh1Yir67Mbf9cQ1OIasr8btaewRhp2WrtdEQU+HqCCMQsl03uhLplU4Vei5Orm3KBAClojBod2Z3ux9Cy0EKS+wQvK03j463zv8oCa33/Y/PW7F9YVtxlvM7vGZjR652MVrrtOZ7HW6hl/JOYZoO0oz9WKp3UJils07cX8MQtksbvx03LXSvxAq+KMtIUMkgFYbmFqWAFxqobV+8xPLuEdOz89SP6GqA4+ISU2OqumaJmRAiqo5dezGKqPwPoxkE/DY79kX5qGYk/8Gymrv+GQkx313w8sNMJj3HW0xZDhfIgkrmOOfBnYMzAycop3p2wWNxMeEo9qaRkCi7Nyg9atZbky80BboOd48JY5r+AMdRHm/X7GrbPJG0KNPCUOSEHOHi8soU6wiSpaMvKxKNO4DUMg+ReXN883u2bTKnsyzasisC/UuJCo0vBXZ00yRVYYPhTB16zpfWZWOrIJL5ZnrgTVAAMAajqYFdNdBiJvUUmUWNsSpv+K2UU+1vOjGDCY7XhTYzA4j1YvRYGnrFrJqh4/QivQCNqDuhJ9hI5RalHCkvFBazN1K7cj44ujFz1VB2b+25/Hv0c1qteY4nZUAEMn3E4zK7qSbu8/ETOC6jnpEGxKwfouZ1InpSRlTvDz2P5/I0G3N8rGto0nM0Zdvo0bu5gyrRneuGJtpax/WmM3XN+QAQ46MOn3dQJbt/4TID3fJ+89V4tDEwvqLTjcf9d2Ru9DGfWnrcIanKEhhVuyFqRF+mTMKTq+bEAcUweNqsIYfUYukXK/ay/1Erth6dxWbMGSFi061BzK2/08SUrtGDk3JFA11LjgLYoplm7Zns1MLUianLuBNd3NG95w7W901fMzPY4QhHmiqA6/n3TLBkZYv44cNHhWFDUYi6B5mSsgG+3RXwpNnQ9tEI6EdPzm181pJHoo1mVzaWFJ6fKqyWaaPleCPVX0qYOq2ae7WAbez9j7WtlIGgBvx4rB0gMrG7U8Rsuihgz+bdpl3CL6HsPgDal1gfGw3Q27ybH+ZvVOphUei/YCp3cGQW6PchQr9f9+n9PpKBOENi9KkSj7VkVhslqIbaJ9h4aUSx5fhJzZ+7eiFCypIlaUfyh8KksCNIDrkiPGvBtPN7vUp3TDwBgOjCcY6oK8IXF6bzSIIvdSbYtYDK2NQBgbkYZ3/Rqxi+xWnJb0WdEYtqFmvw34yCV4vZ9s2TNPJfGJOseZcSjfI2kXgvUFuwNQyAZ4lB1yzpgyyfJ6UbeDsOCe/OOB9/oggspGBiyhPRbzo//LUS4hQOcleRjIcgTI9LjA1NyO1fNUf6PMZ/yuiH9AQCbMpEF8ZV5WYNi6AT5Puip+fXKWhTgpM5oWJ6AtMsk0jNdH1uhgCA56ynUds5wbGptAL1sW0GFncei1kqtLYgqyowMTVL9+9bjc+XPuLFLQRQ4rp9btH9HRIiHl5uYziV5BI+xBSQ5j7KllnTsGKWxQxHz4trRXNxPs3Jdno+qwCOdIrwFlbcUKnc5U4CqsvETNpnB7enUbUnUwCyXpqNCvJ7G1Z84nAKAQ6Uu6KlcUljRaYWcXJFTcBGmKhh95w6vmynewBJjioT3PgpxdpmYjlUHIROQoHe7z7Df7rDeNUYwBJtYjtxk5f+7Fyd6GvbKlbE59IK4w2hDGYXaYP6uZreGTGrCUa+cQAZ6Vj0V5nwyoNib2qo2sH0RuGlxbGLaHPSz+bc1frLYk2wjFCUTByXcGVF6sXjIRkqBxm309IhW4k3a+cdH+UtGDzxk77m+J5J6L/aUv9ZOwZx2/OeLfocKKyUa1CmxrHffrxo/+vwYXeUM+cbScOOQRfsq/VXeh2BkeQF4PPFeXmwXuSjOSgHIOBQJCwcHZaptuxgsnsvmZOHm7BGqmb2+gCrZ4wgV5OYNJQ56zjZgCE/5n/3V12C59yfoaV8JlDf63uWE6p70Ohau7y4vpnY6nQIkSC/SflS6nQhRg3Yx6IDJTUcyKIlMFpK0OBHrnD05AOoB61qObbsZx4xBDlPETOzFn+9/igw04e1+KA0uHKcHHWQ8vtWdt2K8JD3xZo6ZJZZ1EN0Gt1p8qUH5KrBrGm51k0OxS5y0dHcT/Fa4E4tDycYW89BfaYrrF3zBRJ4CXWHwj93ovk2al+iCBN7mOtuAk/ftYWFfe+VfxyfsT2PdIyhtgz+0MyvW1hMKtlzpfy6njNXKet7sXczc4jqXXIdIco4AUyWQNiwHXPID/ryroFchyfIPbvXJI2zvZA7wQSy07F06S3TsvuCiKWJrBullvZ/TKqy+DDB5nx20CEfrRMUjrgjOz/z100ql0JDHfgRXMrLSLd20fs9QRIKtLVQI9/iXM2ASf3/fqxDmbus++yMKeorD/Ik4SVNC+nQC6DgCenOmQmzssa3z0VZfBCHmB3iPWTmAQvbjXbz0q4qGwhVQm2ALZBb9aAE2N/2jHhutR8hvFXanQAvywVisSLGJhhngKUP6EdPFKgE/IH56drqo7sVMlPBthjbOgbwQRw3gzVDshXD9DunD4HuiKI26CWkQt9JjWlnyLY9xCjgg7mADOej7dlKl+55zr7sUiBPfVcz47VCpTR9BZH+h6PZIzv7lc6Nb+TmTFcsyIUe+ySHPsyEL43FuUC3M3Mg6TKioNLqfppejRKP/gk4CvhATR2E1NiCpf8zfcUvfxBSDeAibvVb7RkBT9Mahf7AOUfSfm66tq4xOdX6DrLKtw2y2hR0SeWGBweWR/O1ri9h8YjolCJxPCq0t8hlnyROjl/EZwiZKfpPEo3DqC9UlUrTYn6DGbCjvhtDDTfbLx8HZq65zDy0Kg3v9L5pftj/4rZoiJ7cQ92XXZ7txvdCoz1Xpyx3ECrPaJKHYY1FyzJTxOS5MNDXI7YfxTWtn5qQlNjbl4s6CVwqwx8qPf2BtlFI4RJOYYbWW5N1+oZi8woltkHAqxDjd4HOjC+AzFccHZYLtJ3xbVtovSd7jiw9zcFzuMSJITlF3m3hD20Or3LctLtefjt/f2OM0Ag/osw5b7PpHt7Qj6vxY0QHqU5++xntNsaZd2+gHpdGnp8n+4fDF5byNG2ENxFK801wWJSZeO1lb6TatmQjPbch0ZIgcTKna+hJ5Ox8toQlhva1FZS5MiChyPTzLzoE/qkrxssXJl/8yLHzurzvcvjTa+UO8TmvEQbvHq0D3r8u3cvKqXwQdVgLm9hG0mjaPWMouhHkyBVVRCpKBOxGXFLguczMl/qWUVOFy8MSHCrAgzIvvgF6uxZg0pb0cqwXre8tX6cwXCJvkVdzUFCVWasRYkDsn1svcsdC2AmSW1UcOu96GBL7XDsSf8yZmN31wTl45vDdmhsVnf1Q830o4h4iD6ZQZIBNmwdNFdq51EKYhhWo646oie66fhY2S/FQTHrfAeTrgJE0xUSQ/5yWue7WGC3Kz5R+hErbd23UQ8HMd1tkJrpxai75yyPjwn9f7CxQuffUsA8N6/qpnJpcioI9jUz76JeXL5c27RMGxd2HGkjsp2qrCJCpOCAkynka+iLmdwp+aYJzdv+D/Tk3ytArMrV2BX3fs0MtMV8zHRVvM8ybVH5ixLpovYhilzKiHSZNGkerrG3JUS7Ccyo3imlabHekZMoHAtH9GlIiAIp2RTAuXQWpd/ibQzp8VsABFb1BXjgxoQkYaBDL6pkTeMRv4nHawcvtXxe/gWceg4LjwcYbtQbCoiKhV/AQuNp/O1OhZyZPxxcW6uYM017n35KacTSdMl/ic5w2VKpxMfkYtG7qVVgCnpk6w+hVKMgfDViYhZvHR5iXBltmBwUOIK7uN1azFVCN7WS+3viA2yHZTyg50mmKv0HRie3N8WCM7VNZiKzTugXvTPlSWX9TWPbZV0ZcXx4p4pFcn1wT5PZVXjoF5uc30WLp4ue+BcUijvMK3mpvNZRzneFxV962vLAgBr1U0XnUl6W+iLdfbnlcLFGJA3HnUdwYlmsE4ZYY4oZu0IB0wKRJG6SXp/uOkZyWCw0n7hLvFAW5rNSfZUy0FnKeNmAHgKUZslesQyECiEbYx2vTYz5Qq5HP1DgQrM5IcKsbQR/fWwwPUdvOi/61bXTJNN6fLg6I3IRxe8n4oFpHF9z25KTeXxwyS5qw/NJNDIoPoJYdf5A2YVwloli0U+yYBnwSqRhR+Nnsg/nRtgLLM4tPpUWXH+HIcOtwa6e0xD14bHaUyHXUA67OS4bT+6KWOuUxIA3/wWreMe54MWQLZcD8+b7sBmrAjhO1GsA8Qsfx4et1HGuJWJGoAy26pVqSUtLRyLPWNqHml7lK5r9s9i6ZLdRaPpv7xw+L+p5hBfcylHkUBVKe/tw7oRvE608YqCykdYUcNWeTm3eXukTQgrTK4hpdgbswh1FoU8ATphNfRtFRNfH1+Gk9ltCXuMiR2X5WnNag8OVQvSO5l3o144mGeIZYycmhm2Xch4UVkDdbg8QuI22jG2hOPZV+luUYENODxey8AjZ0nFLkHaltcE5ekPBQV7D0deW/R24W7HMFhjIYvUSZvVYKTQgfr9UJ7PAisscjkGJKyymJXeB/lckStdlCaXnt6kcKUYJqg1a8SIF/VcwqoMoHqHWSqmOAWi39+qXwTwEcyG4VLqHaP+YW2gGxmtKjLgFfj9qQKvEpKqhBJS10dOhj2EfwK6VEe4R4QzoP4q9pgsAAKlhX4bQyV7WIhVmbkieDOlCn5QMcTS1kTvUtFFldkGvcF1LmDxL7TdUNtWIZNoDSGZbPvxfJtK9qg+zWvGjVZuSRJJxuBU+7nza9NaE6wt1gtMCnSW2TmWZWNVnpNG1dXOjTDQIwzoBJOpnmaQD6r3xjOoHfdSAPg9+NYM9vrcO3vrMuPoXgPB");
					UploadPersonE upe = new UploadPersonE();
					upe.setUploadPerson(up);
					// 上传
					UploadPersonResponseE response = upds.uploadPerson(upe);
					UploadPersonResponse rsp = response.getUploadPersonResponse();
					String message = rsp.getMessage();
					// System.out.println("message:=="+message);
					if (message.contains("101")) {
						System.out.println(message);
					} else if (message.contains("102")) {
						System.out.println(message);
					} else if (message.contains("103")) {
						System.out.println(message);
					} else if (message.contains("OK")) {
						System.out.println(message);
						// 更新日志表记录返回信息
						for (int j = 0; j < userList.size(); j++) {
							haiyangMapper.updateSendUserResult(userList.get(j).getUserSerial());
						}
					}
				}
				/// ========================================================================================================
				/// 根据项目编号取企业下所有的工人 每次取50条 top50 取修改的
				String xmldata1 = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" + "<data>" + " <List>";
				List<HaiyangWorkerListEntity> userList1 = haiyangMapper.getWorkerListByRegserial(String.valueOf(1),
						String.valueOf(dtProjectRsp.getRegSerial()));
				for (Iterator iterator3 = userList1.iterator(); iterator3.hasNext();) {
					HaiyangWorkerListEntity haiyangWorkerListEntity1 = (HaiyangWorkerListEntity) iterator3.next();
					String staffdata1 = "<Staff>" + "<mig_name>" + haiyangWorkerListEntity1.getMigName() + "</mig_name>"
							+ "<idcard>" + haiyangWorkerListEntity1.getIdCard() + "</idcard>"
							+ "<image>data:image/jpeg;base64,"
							+ ZipUtils.img2Base64(imgpath + "" + haiyangWorkerListEntity1.getImage().replace("..", ""))
							+ "</image>\r\n" + "<home_address>"
							// + "<image>data:image/jpeg;base64," +
							// ZipUtils.img2Base64("D:\\源码-中建对接\\wwwroot\\photo\\20000\\20000541.jpg")
							// + "</image>" + "<home_address>"
							+ haiyangWorkerListEntity1.getHomeAddress() + "</home_address>" + "<national>"
							+ haiyangWorkerListEntity1.getNation() + "</national>" + "<tel>"
							+ haiyangWorkerListEntity1.getTel() + "</tel>" + "<work_type>"
							+ haiyangWorkerListEntity1.getWorkType() + "</work_type>" + "<educ_level>"
							+ haiyangWorkerListEntity1.getEducLevel() + "</educ_level>" + "<plt_type>"
							+ haiyangWorkerListEntity1.getPltType() + "</plt_type>" + "<native_place>"
							+ haiyangWorkerListEntity1.getNativePlace() + "</native_place>" + "<grantorg>"
							+ haiyangWorkerListEntity1.getGrantorg() + "</grantorg>" + "<acct_num>"
							+ haiyangWorkerListEntity1.getAcctNum() + "</acct_num>" + "<work_team>"
							+ haiyangWorkerListEntity1.getWorkTeam() + "</work_team>" + "<isTeamleader>"
							+ haiyangWorkerListEntity1.getIsTeamLeader() + "</isTeamleader>" + "<work_role>"
							+ haiyangWorkerListEntity1.getWorkRole() + "</work_role>" + "<corp_id>"
							+ haiyangWorkerListEntity1.getCorpId() + "</corp_id>" + "<own_corp>"
							+ haiyangWorkerListEntity1.getOwnCorp() + "</own_corp>" + "<status>"
							+ haiyangWorkerListEntity1.getStatus() + "</status>" + "<contract_labor>"
							+ haiyangWorkerListEntity1.getContractLabor() + "</contract_labor>" + "<entry_time>"
							+ haiyangWorkerListEntity1.getEntryTime() + "</entry_time>" + "<exit_time>"
							+ haiyangWorkerListEntity1.getExitTime() + "</exit_time>" + "</Staff>";
					xmldata1 += staffdata1;
				}
				xmldata1 += "</List>" + "</data>";
				// UploadPersonDetailsService upds1 = new
				// UploadPersonDetailsService();
				// UploadPersonDelegate soap1=upds.getUploadPersonDetails();
				// String message1=soap1.uploadPerson(dtProjectRsp.getToken(),
				// TokenAES.encrypt(TokenAES.gzip(xmldata1),
				// dtProjectRsp.getToken()));
				if (userList1.size() > 0) {
					UploadPersonDetailsServiceStub upds1 = new UploadPersonDetailsServiceStub();
					UploadPerson up1 = new UploadPerson();
					up1.setToken(dtProjectRsp.getToken());
					up1.setData(TokenAES.encrypt(TokenAES.gzip(xmldata1), dtProjectRsp.getToken()));
					UploadPersonE upe1 = new UploadPersonE();
					upe1.setUploadPerson(up1);
					// 上传
					UploadPersonResponseE response1 = upds1.uploadPerson(upe1);
					UploadPersonResponse rsp1 = response1.getUploadPersonResponse();
					String message1 = rsp1.getMessage();
					if (message1.contains("101")) {
						System.out.println(message1);
					} else if (message1.contains("102")) {
						System.out.println(message1);
					} else if (message1.contains("103")) {
						System.out.println(message1);
					} else if (message1.contains("OK")) {
						System.out.println(message1);
						// 更新日志表记录返回信息
						for (int j = 0; j < userList1.size(); j++) {
							haiyangMapper.updateSendUserResult(userList1.get(j).getUserSerial());
						}
					}
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

	// **************************************************************************
	public void uploadAttendanceInfoPort() {// 8-工人考勤
											// 0企业1项目2人员3参建单位4培训5班组6工人进退场7工人合同8工人考勤
		try {
			/// 循环海阳的所有项目
			List<DtProjectRsp> prolist = haiyangMapper.getProjectList();
			for (Iterator iterator = prolist.iterator(); iterator.hasNext();) {
				DtProjectRsp dtProjectRsp = (DtProjectRsp) iterator.next();
				// 根据企业号取企业下所有人的考勤记录
				List<HaiyangRecordListEntity> recordlist = haiyangMapper
						.getHaiyangRecordList(String.valueOf(dtProjectRsp.getRegSerial()));
				for (Iterator iterator2 = recordlist.iterator(); iterator2.hasNext();) {
					HaiyangRecordListEntity haiyangRecordListEntity = (HaiyangRecordListEntity) iterator2.next();
					String xmldata1 = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\r\n" + "<data>\r\n"
							+ "<Project projcode=\"" + haiyangRecordListEntity.getProjcode() + "\">\r\n"
							+ "<Record id=\"" + haiyangRecordListEntity.getId() + "\" projcode=\""
							+ haiyangRecordListEntity.getProjcode() + "\"  idcard=\""
							+ haiyangRecordListEntity.getIdcard() + "\" name=\"" + haiyangRecordListEntity.getName()
							+ "\" organname=\"" + haiyangRecordListEntity.getOrganname() + "\" safecard=\""
							+ haiyangRecordListEntity.getSafecard() + "\" time=\"" + haiyangRecordListEntity.getTime()
							+ "\" ioflag=\"" + haiyangRecordListEntity.getIoflag() + "\"></Record>\r\n"
							+ "</Project>\r\n" + "</data>";
					// UploadAttendanceInfoService uais = new
					// UploadAttendanceInfoService();
					// UploadAttendanceInfoDelegate uaid =
					// uais.getUploadAttendanceInfoPort();
					// String result =
					// uaid.uploadAttdInfo(haiyangRecordListEntity.getToken(),
					// xmldata1);
					UploadAttendanceInfoServiceStub uaiss = new UploadAttendanceInfoServiceStub();
					UploadAttdInfo ua = new UploadAttdInfo();
					ua.setArg0(haiyangRecordListEntity.getToken());
					ua.setArg1(xmldata1);
					UploadAttdInfoE uai = new UploadAttdInfoE();
					uai.setUploadAttdInfo(ua);
					// 上传
					UploadAttdInfoResponseE response = uaiss.uploadAttdInfo(uai);
					//
					UploadAttdInfoResponse rsp = response.getUploadAttdInfoResponse();
					String result = rsp.get_return();
					// System.out.println("token:="+haiyangRecordListEntity.getToken());
					// System.out.println("xmldata1:="+xmldata1);

					if (result.contains("101")) {
						System.out.println(result);
					} else if (result.contains("102")) {
						System.out.println(result);
					} else if (result.contains("103")) {
						System.out.println(result);
					} else if (result.contains("ok")) {
						System.out.println("成功");
						// 更新日志表记录返回信息
						for (int j = 0; j < recordlist.size(); j++) {
							haiyangMapper.updateWorkerJlBybhStatus(Integer.parseInt(recordlist.get(j).getId()));
						}
					}
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
	}

}