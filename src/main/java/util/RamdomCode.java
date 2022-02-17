package util;

public class RamdomCode {
	
	public String genAuthCode() {
		StringBuffer code = new StringBuffer("");
		for (int i = 0; i < 8; i++) {    // 產生8位數			
			int rad = (int)(Math.random() * 3) + 1;    //判斷要大寫小寫英文字還是數字
			switch (rad) {
			case 1:
				code.append((char)((int)(Math.random() * 26) + 65));  // 大寫的字元10進位表示從65-90
				break;
			case 2:
				code.append((char)((int)(Math.random() * 26) + 97));  // 小寫的字元10進位表示從97-122
				break;
			case 3:
				code.append((int)(Math.random() * 10));   // 數字0~9
				break;
			}
		}
		return code.toString();		
	}
}
