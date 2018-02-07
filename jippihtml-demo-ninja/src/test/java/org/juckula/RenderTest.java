///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package org.juckula;
//
//import testtemplates.LandingPage;
//import java.io.ByteArrayOutputStream;
//import java.nio.charset.Charset;
//import org.hamcrest.CoreMatchers;
//import org.junit.Test;
//import static org.junit.Assert.*;
//
///**
// *
// * @author ra
// */
//public class RenderTest {
//    
//    public RenderTest() {
//    }
//
//    @Test
//    public void testSomeMethod() throws Exception {
//        
//        LandingPage render = new LandingPage("title");
//        
//        System.out.println(render.writeOut());
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        
//        render.writeOut(byteArrayOutputStream);
//        
//        String string = new String( byteArrayOutputStream.toByteArray(), Charset.forName("UTF-8"));
//        
//        assertThat(string, CoreMatchers.equalTo("<html><head><title>title<title></head><body><header><p>That's the funky header, dude!</p></header><div class='main'><p>a message</p></div><footer><p>That's the funky footer, dude!</p></footer></body></html>"));
//        assertThat(render.writeOut(), CoreMatchers.equalTo("<html><head><title>title<title></head><body><header><p>That's the funky header, dude!</p></header><div class='main'><p>a message</p></div><footer><p>That's the funky footer, dude!</p></footer></body></html>"));
//        
//    }
//    
//}
