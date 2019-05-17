package cn.stylefeng.guns.core.common.constant.state;

public enum OrderStatus {
	
	Unpaid(1,"未支付"),
	Paid(2,"已支付"),
	Finished(3,"已完成"),
	Canceled(4,"已取消"),
	Refund(5,"退款中"),
	Refunded(6,"已退款"),
	Deleted(7,"已删除");
	
	private Integer code;
	private String name;

	private OrderStatus(Integer code, String name) {
		this.code = code;
		this.name = name;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
