/**
 * 
 */
package hulva.luva.codesample.elasticsearch.formater;

/**
 * @ClassName
 * @Description
 * @author Hulva Luva.H
 * @date 2016年11月26日
 *
 */
public abstract class Formater<T> {
	
	private Format_Type typeCode; 
	
	public enum Format_Type {
		JSON {
			@Override
			public int getTypeCode() {
				return 0;
			}
		},
		String{
			@Override
			public int getTypeCode() {
				return 1;
			}
		};
		
		public int getTypeCode() {
			return -1;
		}
	}
	
	public int getTypeCode() {
		return this.typeCode.getTypeCode();
	}
	
	public void setTypeCode(Format_Type typeCode) {
		this.typeCode = typeCode;
	}

	public void format(StringBuffer sb){
		switch(getTypeCode()){
		case 0:
			new JSONFormater<Object>().doFormat(sb);
		}
	}
	
	public abstract void doFormat(StringBuffer sb);
	
}
