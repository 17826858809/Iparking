package cn.zy.basic.entity;

/**
 * tb_spacestate 车位使用状态表，无使用的车位可被看到
 */
public class SpaceState {
	private int sid;
	private int canuse; //现在是否可用
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public int getCanuse() {
		return canuse;
	}
	public void setCanuse(int canuse) {
		this.canuse = canuse;
	}

}
