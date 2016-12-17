/**
 * 
 */
package hulva.luva.learn.hadoop.mapreduce.avro;

import java.util.Date;

/**
 * @ClassName
 * @Description This class represents a row from the weblog_entries.txt dataset.
 * @author Hulva Luva.H
 * @date 2016年12月16日
 *
 */
public class WeblogRecord {
	private String cookie;
	private String page;
	private Date date;
	private String ip;

	public WeblogRecord() {
	}

	public WeblogRecord(String cookie, String page, Date date, String ip) {
		super();
		this.cookie = cookie;
		this.page = page;
		this.date = date;
		this.ip = ip;
	}

	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Override
	public String toString() {
		return "WeblogRecord [cookie=" + cookie + ", page=" + page + ", date="
				+ date + ", ip=" + ip + "]";
	}

}
