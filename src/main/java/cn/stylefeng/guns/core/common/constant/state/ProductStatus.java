package cn.stylefeng.guns.core.common.constant.state;

public enum ProductStatus {
	
	OK("1", "启用"), FREEZED("0", "冻结");

    String code;
    String message;

    ProductStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
    

}
