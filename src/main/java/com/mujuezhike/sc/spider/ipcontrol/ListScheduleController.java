package com.mujuezhike.sc.spider.ipcontrol;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.LoggerFactory;

public class ListScheduleController {
	
	private final static org.slf4j.Logger logger = LoggerFactory.getLogger(ListScheduleController.class);
	
	public static String lastname = "";//最后处理的文件
	
	public static String listnumpath = "C:\\zklist\\ry";
	
	public static String listnumlogpath = "C:\\zklist\\rylog";
	
	final private static int TASK_MAX_THREAD = 18;
	
	public static int nowpoint = 0;
	
	public static String[] namearr = {
			//"http://www.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			
			"http://bj.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://cq.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://sh.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://tj.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://ah.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://hefei.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://wuhu.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://bangbu.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://huainan.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://mas.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://huaibei.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://tongling.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://anqing.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://huangshan.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://chuzhou.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://fuyang.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://suzhou.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://liuan.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://bozhou.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://chizhou.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://xuancheng.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://fj.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			
			"http://fzh.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://shamen.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://putian.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://sanming.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://quanzhou.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://zhangzhou.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://nanping.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://longyan.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://ningde.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://gd.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://dongguan.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://zhongshan.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://guangzhou.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://shaoguan.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://shenzhen.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://zhuhai.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://shantou.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://foshan.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://jiangmen.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://zhanjiang.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://maoming.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://zhaoqing.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://huizhou.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://meizhou.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://shanwei.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://heyuan.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			
			"http://yangjiang.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://qingyuan.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://chaozhou.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://jieyang.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://yunfu.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://gs.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://jyg.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://lanzhou.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://jinchang.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://baiyin.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://tianshui.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://wuwei.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://zhangye.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://pingliang.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://jiuquan.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://qingyang.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://dingxi.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://longnan.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://lxhz.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://gnzz.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://gx.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://nanning.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://liuzhou.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://guilin.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			
			"http://wuzhou.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://beihai.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://fcg.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://qinzhou.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://guigang.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://yul.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://baise.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://hezhou.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://hechi.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://laibin.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://chongzuo.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://gz.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://guiyang.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://lps.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://zunyi.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://anshun.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://bijie.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://tongren.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://qxnbyz.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://qdnz.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://qnbyz.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://han.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://haikou.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://sanya.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://hanzx.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://heb.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			
			"http://sjz.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://tangshan.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://qhd.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://handan.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://xingtai.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://baoding.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://zjk.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://chengde.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://cangzhou.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://langfang.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://hengshui.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://hen.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://zhengzhou.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://kaifeng.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://luoyang.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://pds.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://anyang.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://hebi.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://xinxiang.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://jiaozuo.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://puyang.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://xuchang.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://luohe.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://smx.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://nanyang.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://shangqiu.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://xinyang.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://zhoukou.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://zmd.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://henzx.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			
			"http://hlj.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://herb.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://qqhe.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://jixi.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://hegang.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://sys.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://daqing.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://yich.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://jms.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://qth.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://mdj.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://heihe.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://suihua.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://dxaldq.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://hub.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://wuhan.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://huangshi.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://shiyan.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://yichang.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://xiangyang.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://ezhou.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			
			"http://jingmen.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://xiaogan.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://jingzhou.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://huanggang.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://xianning.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://suizhou.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://estjz.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://hubzx.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://hun.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://zhangsha.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://zhuzhou.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://xiangtan.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://hengyang.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://shaoyang.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://yueyang.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://changde.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://zjj.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://yiyang.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://chenzhou.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://yongzhou.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://huaihua.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://loudi.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://xxtjz.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://jl.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			
			"http://zhangchun.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://jilin.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://siping.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://liaoyuan.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://tonghua.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://baishan.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://songyuan.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://baicheng.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://ybcxz.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://js.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://nanjing.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://wuxi.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://xuzhou.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://changzhou.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://szh.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://nantong.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://lyg.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://huaian.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://yancheng.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://yangzhou.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://zhenjiang.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://tzh.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://suqian.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://jx.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://nanchang.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://jdz.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://pingxiang.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://jiujiang.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://xinyu.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://yingtan.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://ganzhou.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://jian.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://ych.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://fuzhou.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://shangrao.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://ln.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://shenyang.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://dalian.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://anshan.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://fushun.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://benxi.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://dandong.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://jinzhou.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://yingkou.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://fuxin.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://liaoyang.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://panjin.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://tieling.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://chaoyang.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://hld.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://nmg.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://hhht.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://baotou.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://wuhai.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://chifeng.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://tongliao.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://eeds.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://hlbe.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://byne.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://wlcb.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://xam.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://xlglm.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://alsm.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://nx.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://yinchuan.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://szs.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://wuzhong.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://guyuan.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://zhongwei.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://qh.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://xining.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://haidong.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://hbzz.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://hunzz.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://hnzz.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://glzz.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://yszz.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://hxmgz.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://sc.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://chengdu.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://zigong.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://pzh.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://luzhou.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://deyang.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://mianyang.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://guangyuan.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://suining.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://neijiang.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://leshan.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://nanchong.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://meishan.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://yibin.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://guangan.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://dazhou.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://yaan.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://bazhong.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://ziyang.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://abzzqz.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://gzzz.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://lsyz.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://sd.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://jinan.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://qingdao.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://zibo.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://zaozhuang.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://dongying.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://yantai.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://weifang.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://jining.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://taian.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://weihai.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://rizhao.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://laiwu.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://linyi.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://dezhou.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://liaocheng.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://binzhou.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://heze.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://snx.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://xian.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://tongchuan.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://baoji.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://xianyang.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://weinan.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://yanan.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://hanzhong.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://yulin.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://ankang.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://shangluo.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://sx.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://taiyuan.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://datong.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://yangquan.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://zhangzhi.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://jincheng.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://shuozhou.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://jinzhong.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://yuncheng.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://xinzhou.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://linfen.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://lvliang.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://xj.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://wlmq.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://klmy.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://tlfdq.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://hmdq.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://cjhz.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://betlmg.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://byglmg.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://aksdq.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://kzlskek.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://ksdq.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://htdq.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://ylhsk.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://tcdq.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://altdq.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://xjzx.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://xz.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://lasa.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://rkz.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://cddq.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://sndq.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://nqdq.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://aldq.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://lzdq.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://yn.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://kunming.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://qujing.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://yuxi.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://baoshan.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://zhaotong.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://lijiang.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://puer.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://lincang.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://cxyz.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://hhhnzyz.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://wszzmz.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://xsbndz.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://dlbz.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://dhdzjpz.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://njlsz.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://dqzz.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://zj.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://hangzhou.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://ningbo.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://wenzhou.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://jiaxing.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://huzhou.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://shaoxing.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://jinhua.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://quzhou.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://zhoushan.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://taizhou.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://lishui.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company",
			"http://baotou.tianyancha.com/search/ola1?key=%E4%B9%B3%E4%B8%9A&searchType=company"
	};
	
	public static void init(){
		
		
		
	}

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		
		DynamicIpContainer.init();
		Thread.sleep(35000);
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(TASK_MAX_THREAD);  
		  
			for (;;) {  
				//logger.info("boforestart:"+index);
				int threadCount = ((ThreadPoolExecutor)fixedThreadPool).getActiveCount();
				Thread.sleep(500);
				//System.out.println(threadCount);
				
				if(threadCount < TASK_MAX_THREAD  && nowpoint < namearr.length){//@20170714 最后增加   && nowpoint < namearr.length  以前不能结束  未测试
					logger.info("nowpoint:"+nowpoint);
					ListSearchTYCCompanyTask task = new ListSearchTYCCompanyTask();
					String[] srr = getNextURLsFromFileName();
					Random rd = new Random();
					int ioe = rd.nextInt(5);
					if(srr!=null && srr.length>0){
						task.setStrs(srr);
						task.setEer(ioe);
						fixedThreadPool.execute(task);
					}
				}
				
		    }  

	}
	
	public static String[] getNextURLsFromFileName() throws IOException{
		
		String[] slist = new String[5];
		int me = namearr.length;
		int m = 0;
		for(int i = nowpoint ;(i<nowpoint+5) && (i < me);i++,m++){
			slist[m] = namearr[i];
		}
		
		nowpoint += 5;
          
		return slist;
		
	}

}
