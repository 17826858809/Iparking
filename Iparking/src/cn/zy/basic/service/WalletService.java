package cn.zy.basic.service;

import java.util.List;

import cn.zy.basic.dao.WalletDao;
import cn.zy.basic.entity.Wallet;

public class WalletService {
	private WalletDao walletDao=new WalletDao();
	//通过uerid查询
	public Wallet findMoney(Wallet wallet){
		try {
			return walletDao.findMoney(wallet);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//更新余额
	public int upMoney(Wallet wallet){
		try {
			return walletDao.upMoney(wallet);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//查询所有
	public List<Wallet> findAll(){
		try {
			return walletDao.findAll();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//通过租金查询
	public List<Wallet> findByDeposit(double deposit){
		try {
			return walletDao.findByDeposit(deposit);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//userid+deposit查询
	public Wallet findByUD(int id,double deposit){
		try {
			return walletDao.findByUD(id,deposit);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//修改
	public int update(Wallet wallet){
		try {
			return walletDao.update(wallet);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public int charge(int money,int userid){
		try {
			return walletDao.charge(money,userid);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}








