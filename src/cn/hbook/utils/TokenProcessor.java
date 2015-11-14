package cn.hbook.utils;


public class TokenProcessor{
	
	public static char[]  letter = {
		'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'g', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 
		's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
		'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'G', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 
		'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
	};
	
	public static char[] digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public static String generateToken(){
    /*    String token = UUID.randomUUID() + "";
        //利用MD5算法，得到数据的摘要
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte[] buff = md.digest(token.getBytes());  //将任意长度的数据，转化成128位的数据 即16个字节
             
            //base64编码  返回键盘上可见的字符组成的字符串  将六位0|1 转化成一个字节。  则其取值范围为[0,63]业绩64个字符 
            //可用于数据的传输， base64编码之后，我们的消息 开石头， 结束为 用不是base64里面的字符即可
            String stoken = new BASE64Encoder().encode(buff);
            return stoken.substring(4, stoken.length() - 10);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }*/
    	return System.currentTimeMillis() + "";
    }
    
    public static void main(String[] args) {
    	System.out.println(System.currentTimeMillis());
    }
    
}