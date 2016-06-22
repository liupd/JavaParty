package com.zp.tent.common.util.ip;
/**
 * @author LJ-silver
 */
import java.util.*;
public class Test {

	public static void main(String[] args) {
		
		args = new String[]{"ip", "9.128.2.1"};
		
		IPSeeker seeker = IPSeeker.getInstance();

		if(args.length==2){
			if("ip".equals(args[0])){
				System.out.println(args[0]+"的所在地址是:"+seeker.getAddress(args[1]));
                            System.out.println(args[0]+"的所在地址是属于:"+seeker.getCountry(args[1]));
			}else if("address".equals(args[0])){
				List a = seeker.getIPEntries(args[1]);
System.out.println(args[0]+":");
				for(int i=0;i<a.size();i++){
					System.out.println(a.get(i).toString());
				}
			}else{
				System.out.println("usage:java Test ip/address yourIpString/yourAddressString");
			}
		}else{
			System.out.println("usage:java Test ip/address yourIpString/yourAddressString");
	 
              }
 }
}

 
