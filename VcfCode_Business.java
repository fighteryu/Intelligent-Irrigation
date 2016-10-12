package com.pz.qrcode.businessImpl;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.imageio.ImageIO;

import pz.jar.unite.exception.ChecksException;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class VcfCodeBusinessImpl {

		public  void main(String[] args) {
			String name = "张三";
			try {
				//将字符串转换成utf-8 quoted-printable 格式
				String str = qrencodeingUTF8(name);
				System.out.println(str);
				//将字符串utf-8 quoted-printable 格式 转换成正常格式
				str =  qrdecodingUTF8(str);
				System.out.println(str);
				String path ="G:\\wsp.png";
				//将图片转换成base64格式字符
				str =BASE64Encodeing(path);
				System.out.println(str);
				//将base64格式字符转换成图片
				 BASE64Decoding(str,"png", path);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * 编码 图片 转换为 BASE64 
		 * @param path 图片路径
		 * @return 转换后的字符串
		 */
		public  String BASE64Encodeing(String path) throws ChecksException  {
			BASE64Encoder encoder = new BASE64Encoder();
			File file = new File(path);
			try {
				FileInputStream fis = new FileInputStream(file);
				byte[] buffer = new byte[(int) file.length()];
					fis.read(buffer);
				return encoder.encode(buffer);
			} catch (FileNotFoundException e) {
				throw new ChecksException("FileNotFound");
			}catch (IOException e) {
				throw new ChecksException("io.error");
			}
		}

		/**
		 * 解码 BASE64 保存为图片
		 * @param str 需要解码的字符串
		 * @param imgType 解码图片类型
		 * @param path 解码后图片保存的位置
		 * @return 解码是否完成
		 * @throws Exception
		 */
		public   boolean BASE64Decoding(String str, String imgType, String path) throws Exception {
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] ib = decoder.decodeBuffer(str);
			BufferedImage bi = ImageIO.read(new ByteArrayInputStream(ib));
			boolean isFinish = ImageIO.write(bi, imgType, new File(path));
			bi.flush();
			return isFinish;
		}

		/**
		 * 编码 UTF8 quoted-printable 
		 * @param str 需要编码的字符串
		 * @return 编码后的字符串
		 * @throws Exception
		 */
		public   String qrencodeingUTF8(String str) {
			if (str != null) {
				char[] encode = str.toCharArray();
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < encode.length; i++) {
					if ((encode[i] >= '!') && (encode[i] <= '~')
							&& (encode[i] != '=') && (encode[i] != '\n')) {
						sb.append(encode[i]);
					} else if (encode[i] == '=') {
						sb.append("=3D");
					} else if (encode[i] == '\n') {
						sb.append("\n");
					} else {
						StringBuffer sbother = new StringBuffer();
						sbother.append(encode[i]);
						String ss = sbother.toString();
						byte[] buf = null;
						try {
							buf = ss.getBytes("utf-8");
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
						// UTF-8: buf.length == 3
						// GBK: buf.length == 2
						if (buf.length == 3) {
							for (int j = 0; j < buf.length; j++) {
								String s16 = String.valueOf(Integer
										.toHexString(buf[j]));
								char c16_6;
								char c16_7;
								if (s16.charAt(6) >= 97 && s16.charAt(6) <= 122) {
									c16_6 = (char) (s16.charAt(6) - 32);
								} else {
									c16_6 = s16.charAt(6);
								}
								if (s16.charAt(7) >= 97 && s16.charAt(7) <= 122) {
									c16_7 = (char) (s16.charAt(7) - 32);
								} else {
									c16_7 = s16.charAt(7);
								}
								sb.append("=" + c16_6 + c16_7);
							}
						}
					}
				}
				str = sb.toString();
			}
			return str;
		}

		/**
		 * 解码 UTF8 quoted-printable 
		 * @param str 需要解码的字符串
		 * @return 解码后的字符串
		 * @throws Exception
		 */
		public   String qrdecodingUTF8(String str) throws Exception {
			if (str != null) {
				StringBuffer sb = new StringBuffer(str);
				for (int i = 0; i < sb.length(); i++) {
					if (sb.charAt(i) == '\n' && sb.charAt(i - 1) == '=') {
						sb.deleteCharAt(i - 1);
					}
				}
				str = sb.toString();
				byte[] bytes = str.getBytes("US-ASCII");
				for (int i = 0; i < bytes.length; i++) {
					byte b = bytes[i];
					if (b != 95) {
						bytes[i] = b;
					} else {
						bytes[i] = 32;
					}
				}
				if (bytes != null) {
					ByteArrayOutputStream buffer = new ByteArrayOutputStream();
					for (int i = 0; i < bytes.length; i++) {
						int b = bytes[i];
						if (b == '=') {
							try {
								int u = Character.digit((char) bytes[++i], 16);
								int l = Character.digit((char) bytes[++i], 16);
								if (u == -1 || l == -1) {
									continue;
								}
								buffer.write((char) ((u << 4) + l));
							} catch (ArrayIndexOutOfBoundsException e) {
								e.printStackTrace();
							}
						} else {
							buffer.write(b);
						}
					}
					str = new String(buffer.toByteArray(), "UTF-8");
				}
			}
			return str;
		}

}

