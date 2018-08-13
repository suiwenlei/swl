package com.leidengyun.socket.util;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.UUID;

import com.leidengyun.mvc.util.StringUtils;

/**
 * 扩展StringUtils方法
 *
 */
public class newStringUtil extends StringUtils {

	private final static char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
			'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

	/** 秘钥Key */
	public static final String SECRET_KEY = "_SecretKey_";

	/**
	 * 将字节转换成16进制显示
	 *
	 * @param b
	 *            byte
	 * @return String
	 */
	public static String toHex(byte b) {
		final char[] buf = new char[2];
		for (int i = 0; i < 2; i++) {
			buf[1 - i] = digits[b & 0xF];
			b = (byte) (b >>> 4);
		}
		return new String(buf);
	}

	/**
	 * 以16进制 打印字节数组
	 *
	 * @param bytes
	 *            byte[]
	 */
	public static String toHexString(final byte[] bytes) {
		final StringBuffer buffer = new StringBuffer(bytes.length);
		buffer.append("\r\n\t   0  1  2  3  4  5  6  7  8  9  a  b  c  d  e  f\r\n");
		int startIndex = 0;
		int column = 0;
		for (int i = 0; i < bytes.length; i++) {
			column = i % 16;
			switch (column) {
			case 0:
				startIndex = i;
				buffer.append(fixHexString(Integer.toHexString(i), 8)).append(": ");
				buffer.append(toHex(bytes[i]));
				buffer.append(" ");
				break;
			case 15:
				buffer.append(toHex(bytes[i]));
				buffer.append(" ; ");
				buffer.append(filterString(bytes, startIndex, column + 1));
				buffer.append("\r\n");
				break;
			default:
				buffer.append(toHex(bytes[i]));
				buffer.append(" ");
			}
		}
		if (column != 15) {
			for (int i = 0; i < 15 - column; i++) {
				buffer.append("   ");
			}
			buffer.append("; ").append(filterString(bytes, startIndex, column + 1));
			buffer.append("\r\n");
		}

		return buffer.toString();
	}

	/**
	 * 过滤掉字节数组中0x0 - 0x1F的控制字符，生成字符串
	 *
	 * @param bytes
	 *            byte[]
	 * @param offset
	 *            int
	 * @param count
	 *            int
	 * @return String
	 */
	private static String filterString(final byte[] bytes, final int offset, final int count) {
		final byte[] buffer = new byte[count];
		System.arraycopy(bytes, offset, buffer, 0, count);
		for (int i = 0; i < count; i++) {
			if (buffer[i] >= 0x0 && buffer[i] <= 0x1F) {
				buffer[i] = 0x2e;
			}
		}
		return new String(buffer);
	}

	/**
	 * 将hexStr格式化成length长度16进制数，并在后边加上h
	 *
	 * @param hexStr
	 *            String
	 * @return String
	 */
	private static String fixHexString(final String hexStr, final int length) {
		if (hexStr == null || hexStr.length() == 0) {
			return "00000000h";
		} else {
			final StringBuffer buf = new StringBuffer(length);
			final int strLen = hexStr.length();
			for (int i = 0; i < length - strLen; i++) {
				buf.append("0");
			}
			buf.append(hexStr).append("h");
			return buf.toString();
		}
	}

	public static String getString(ByteBuffer buffer) {

		Charset charset = null;
		CharsetDecoder decoder = null;
		CharBuffer charBuffer = null;

		try {
			charset = Charset.forName("UTF-8");
			decoder = charset.newDecoder();
			charBuffer = decoder.decode(buffer.asReadOnlyBuffer());
			return charBuffer.toString();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static String[] bytesToHexStrings(byte[] src) {
		if ((src == null) || (src.length <= 0)) {
			return null;
		}
		String[] str = new String[src.length];
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				str[i] = "0";
			}
			str[i] = hv;
		}
		return str;
	}

	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder();
		if ((src == null) || (src.length <= 0)) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	public static String hexStringToString(String hexString, int encodeType) {
		String result = "";
		int max = hexString.length() / encodeType;
		for (int i = 0; i < max; i++) {
			char c = (char) hexStringToAlgorism(hexString.substring(i * encodeType, (i + 1) * encodeType));
			result = result + c;
		}
		return result;
	}

	public static int hexStringToAlgorism(String hex) {
		hex = hex.toUpperCase();
		int result = 0;

		int max = hex.length();
		for (int i = max; i > 0; i--) {
			char c = hex.charAt(i - 1);
			int algorism = 0;
			if ((c >= '0') && (c <= '9')) {
				algorism = c - '0';
			} else {
				algorism = c - '7';
			}
			result = (int) (result + Math.pow(16.0D, max - i) * algorism);
		}
		return result;
	}

	public static long bigBys2Uint32(byte[] bys, int off, int len) {
		long uint32 = 0L;
		int i = 0;
		int end = len - 1;
		for (int c = end; i <= end; c--) {
			uint32 |= (0xFF & bys[(off + i)]) << 8 * c;
			i++;
		}
		return uint32;
	}

	public static byte[] hexStringToByte(String hex) {
		int len = hex.length() / 2;
		byte[] result = new byte[len];
		char[] achar = hex.toCharArray();
		for (int i = 0; i < len; i++) {
			int pos = i * 2;
			result[i] = ((byte) (toByte(achar[pos]) << 8 | toByte(achar[(pos + 1)])));
		}
		return result;
	}

	private static int toByte(char c) {
		byte b = (byte) "0123456789ABCDEF".indexOf(c);
		return b;
	}

	public static Integer ToHexString(Integer instr) {
		if (instr != null && (instr.intValue() < 0)) {
			String hexString = Integer.toHexString(instr.intValue());

			char[] charArray = hexString.toCharArray();
			for (int i = 0; i < charArray.length; i++) {
				if (charArray[i] != 'f') {
					hexString = hexString.substring(i);
					break;
				}
				if (i >= charArray.length - 2) {
					hexString = hexString.substring(i);
					break;
				}
			}
			instr = Integer.valueOf(Integer.parseInt(hexString, 16));
		}
		return instr;
	}

	public static String getUuid() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}

	/**
	 * 按指定的字节数截取字符串（一个中文字符占3个字节，一个英文字符或数字占1个字节）
	 * 
	 * @param sourceString
	 *            源字符串
	 * @param cutBytes
	 *            要截取的字节数
	 * @return
	 */
	public static String cutString(String sourceString, int cutBytes) {
		if (sourceString == null || "".equals(sourceString.trim())) {
			return "";
		}

		int lastIndex = 0;
		boolean stopFlag = false;

		int totalBytes = 0;
		for (int i = 0; i < sourceString.length(); i++) {
			String s = Integer.toBinaryString(sourceString.charAt(i));
			if (s.length() > 8) {
				totalBytes += 3;
			} else {
				totalBytes += 1;
			}

			if (!stopFlag) {
				if (totalBytes == cutBytes) {
					lastIndex = i;
					stopFlag = true;
				} else if (totalBytes > cutBytes) {
					lastIndex = i - 1;
					stopFlag = true;
				}
			}
		}

		if (!stopFlag) {
			return sourceString;
		} else {
			return sourceString.substring(0, lastIndex + 1);
		}
	}

	public static String addZeroForNum(String str, int strLength) {
		int strLen = str.length();
		StringBuffer sb = null;
		while (strLen < strLength) {
			sb = new StringBuffer();
			sb.append("0").append(str);
			str = sb.toString();
			strLen = str.length();
		}
		return str;
	}

	/**
	 * byte[]转int
	 */
	public static int bytesToInt(byte[] bytes) {
		int i;
		i = (int) ((bytes[0] & 0xff) | ((bytes[1] & 0xff) << 8) | ((bytes[2] & 0xff) << 16)
				| ((bytes[3] & 0xff) << 24));
		return i;
	}

	/**
	 * 判断传进来的字符串，是否 大于指定的字节，如果大于递归调用 直到小于指定字节数 ，一定要指定字符编码，因为各个系统字符编码都不一样，字节数也不一样
	 * 
	 * @param s
	 *            原始字符串
	 * @param num
	 *            传进来指定字节数
	 * @return String 截取后的字符串
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public static String idgui(String str, int end) throws Exception {

		int length = 0;
		char[] ch = str.toCharArray();
		String str2 = "";
		for (int i = 0; i < str.length(); i++) {
			// int num = Character.codePointAt( ch,i);
			// int num = Character.codePointAt( str,i);
			int num = str.codePointAt(i); // 改行代码与上两行代码实现的效果一致，获取当前遍历元素的值。小于等于255是一个字节，超过255是两个字节
			if (num >= 0 && num <= 255) {
				length++;// 字节数加1
			} else {
				length += 2;// 字节数加2
			}
			if (length < end) { // 没有达到截取字节长度继续循环，并将遍历的元素与str字符串连接
				str2 += ch[i];
			} else if (length == end) { // 正好达到截取字节长度，就退出循环，并将遍历的元素与str字符串连接
				str2 += ch[i];
				break;
			} else { // 超过长度，是因为当前遍历的是中文字符元素，不截取半个中文，所以直接退出循环。
				break;
			}
		}
		return str2; // 返回拼接的新字符串
	}

	public static byte[] longToByteArray(long s) {
		byte[] buffer = new byte[4]; 
		for (int i = 0; i < 4; i++) { 
		int offset = 32 - (i + 1) * 4; 
		buffer[i] = (byte) ((s >> offset) & 0xff); 
		}
		return buffer;
    }
}
