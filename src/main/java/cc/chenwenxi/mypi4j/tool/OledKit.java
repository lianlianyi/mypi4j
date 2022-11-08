package cc.chenwenxi.mypi4j.tool;

import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.spi.SpiChannel;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.fauxpark.oled.Graphics;
import net.fauxpark.oled.SSD1306;
import net.fauxpark.oled.font.CodePage1252;
import net.fauxpark.oled.transport.SPITransport;
import net.fauxpark.oled.transport.Transport;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Data
@Slf4j
@RequiredArgsConstructor
public class OledKit {
	private static final Transport transport = new SPITransport(SpiChannel.CS0, RaspiPin.GPIO_06, RaspiPin.GPIO_05);
	private static final SSD1306 ssd1306 = new SSD1306(128, 64, transport);

	private @NonNull Integer x;
	private @NonNull Integer y;

	static {
		ssd1306.startup(false);// 启动显示器
	}

	public void text(String line) {
		log.info("isDisplayOn:{}",ssd1306.isDisplayOn());
		log.info("isHFlipped:{}",ssd1306.isHFlipped());
		log.info("isInitialised:{}",ssd1306.isInitialised());
		log.info("isScrolling:{}",ssd1306.isScrolling());
		log.info("isVFlipped:{}",ssd1306.isVFlipped());
		this.text(this.getListStr(line, 125));
	}

	public void start() {
		ssd1306.startup(false);// 启动显示器
		log.info("开启oled");
	}

	public void shutdown() {
		ssd1306.shutdown();// 关闭显示器
		log.info("关闭oled");
	}

	public void text(List<String> lines) {
		Graphics graphics = ssd1306.getGraphics();
		for (int i = 0; i < lines.size(); i++) {
			String line = lines.get(i);
			log.info("第{}行:{}",i,line);
			graphics.text(x, i * 10, new CodePage1252(), line);
		}
		ssd1306.display();
	}

//	public void pic(File file) {
//		BufferedImage read = ImgUtil.read(file);
//		this.pic(read);
//	}
//
//	public void pic(URL url) {
//		BufferedImage read = ImgUtil.read(url);
//		this.pic(read);
//	}

	public void pic(BufferedImage read) {
		Graphics graphics = ssd1306.getGraphics();
		try {
			graphics.image(read, x, y, 64, 64);
			ssd1306.display();
		} catch (IOException e) {
			log.info("打印图片异常", e);
		}
	}

	private List<String> getListStr(String str, int len) {
		List<String> listStr = new ArrayList<>();
		int strLen = str.length();
		int start = 0;
		int num = len;
		String temp = null;
		while (true) {
			try {
				if (num >= strLen) {
					temp = str.substring(start, strLen);
				} else {
					temp = str.substring(start, num);
				}
			} catch (Exception e) {
				log.info("拆分完毕", "");
				break;
			}
			listStr.add(temp);
			start = num;
			num = num + len;
		}
		return listStr;
	}
}
