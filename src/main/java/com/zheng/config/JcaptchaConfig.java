package com.zheng.config;

import com.octo.captcha.component.image.backgroundgenerator.UniColorBackgroundGenerator;
import com.octo.captcha.component.image.color.SingleColorGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.DecoratedRandomTextPaster;
import com.octo.captcha.component.image.textpaster.textdecorator.BaffleTextDecorator;
import com.octo.captcha.component.image.textpaster.textdecorator.TextDecorator;
import com.octo.captcha.component.image.wordtoimage.ComposedWordToImage;
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator;
import com.octo.captcha.engine.GenericCaptchaEngine;
import com.octo.captcha.image.gimpy.GimpyFactory;
import com.octo.captcha.service.multitype.GenericManageableCaptchaService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.awt.*;

/**
 * Created by zhenglian on 2016/9/26.
 */
@Configuration
public class JcaptchaConfig {

    @Bean
    public GenericManageableCaptchaService GenericManageableCaptchaService() {
        GenericManageableCaptchaService service = new GenericManageableCaptchaService(imageEngine(), 180, 100000);
        return service;
    }

    @Bean
    public GenericCaptchaEngine imageEngine() {
        GenericCaptchaEngine imageEngine = new GenericCaptchaEngine(new GimpyFactory[]{gimpyFactory()});
        return imageEngine;
    }

    @Bean
    public GimpyFactory gimpyFactory() {
        GimpyFactory factory = new GimpyFactory(randomWordGenerator(), composedWordToImage());
        return factory;
    }

    @Bean
    public RandomWordGenerator randomWordGenerator() {
        RandomWordGenerator generator = new RandomWordGenerator("0123456789abcdefghijklmnopqrstuvwxyz");
        return generator;
    }

    @Bean
    public ComposedWordToImage composedWordToImage() {
        ComposedWordToImage wordToImage = new ComposedWordToImage(randomFontGenerator(),
                uniColorBackgroundGenerator(), decoratedRandomTextPaster());
        return wordToImage;
    }

    @Bean
    public RandomFontGenerator randomFontGenerator() {
        Font font = new Font("Arial", 0, 20);
        RandomFontGenerator generator = new RandomFontGenerator(20, 20, new Font[]{font});
        return generator;
    }

    @Bean
    public UniColorBackgroundGenerator uniColorBackgroundGenerator() {
        UniColorBackgroundGenerator generator = new UniColorBackgroundGenerator(80, 32);
        return generator;
    }

    @Bean
    public DecoratedRandomTextPaster decoratedRandomTextPaster() {
        DecoratedRandomTextPaster paster = new DecoratedRandomTextPaster(4, 4, singleColorGenerator(),
                new TextDecorator[] {baffleTextDecorator()});
        return paster;
    }

    @Bean
    public BaffleTextDecorator baffleTextDecorator() {
        Color whiteColor = new Color(255,255,255);
        BaffleTextDecorator decorator = new BaffleTextDecorator(1, whiteColor);
        return decorator;
    }

    @Bean
    public SingleColorGenerator singleColorGenerator() {
        Color color = new Color(105, 105, 105);
        SingleColorGenerator generator = new SingleColorGenerator(color);
        return generator;
    }





}
