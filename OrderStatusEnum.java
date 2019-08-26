package com.jhzz.service.common.enums;

public enum OrderStatusEnum {

	COMMITED("commited", "待支付"), PAYED("payed", "待供应商确认"), CONFIRMED("confirmed", "待供应商发货"), DELIVERED("delivered",
			"供应商已发货"), FINISHED("finished", "交易完成"), CANCELLED("cancelled", "已取消");

	private String name;
	private String desc;

	private OrderStatusEnum(String name, String desc) {
		this.name = name;
		this.desc = desc;
	}

	public static OrderStatusEnum[] getOrderStatusEnumList() {
		return values();
	}

	public static OrderStatusEnum getEnumByName(String name) {
		for (OrderStatusEnum each : values()) {
			if (each.getName().equals(name)) {
				return each;
			}
		}

		return null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
