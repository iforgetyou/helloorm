package com.zy17.controller;

import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.urlfetch.HTTPHeader;
import com.google.appengine.api.urlfetch.HTTPRequest;
import com.zy17.protobuf.domain.Eng;
import com.zy17.service.CardService;
import org.apache.http.HttpEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.jdo.PersistenceManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: yan.zhang
 * Date: 13-9-17
 * Time: 上午11:20
 */
@Controller
@RequestMapping("/cards")
public class CardController {
    @Autowired
    private CardService cardService;

    @Autowired
    protected PersistenceManagerFactory persistenceManagerFactory;

    @ResponseStatus(value = HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public
    @ResponseBody
    void createPerson(@RequestBody Eng.Card card) {
        this.cardService.add(card);
    }

//    @RequestMapping(value="/rand", method = RequestMethod.POST)
//    public
//    @ResponseBody
//    String sms(HttpServletRequest request){
//        String rand_code = request.getParameter("rand_code");
//        String identifier = request.getParameter("identifier");
//        Eng.Card card = Eng.Card.newBuilder().setEngText(rand_code + identifier).build();
//        this.cardService.add(card);
//        return "{\"res_code\":\"0\"}";
//    }

    @RequestMapping(value = "/random", method = RequestMethod.GET)
    public
//    @ResponseBody
    Eng.Card getOneRandomCard(RedirectAttributes redirectAttrs, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
//        String uploadUrl = blobstoreService.createUploadUrl("/upload");
//        URLFetchService urlFetch = URLFetchServiceFactory.getURLFetchService();
//        HTTPRequest httpRequest = new HTTPRequest(new URL(uploadUrl), HTTPMethod.POST, FetchOptions.Builder.withDeadline(10));
////        httpRequest.setHeader(new HTTPHeader("Content-Type", "multipart/form-data"));
//
////        FileBody contentBody = new FileBody(new File("E:\\test.png"), ContentType.create("image/ png"));
//        ByteArrayBody byteArrayBody = new ByteArrayBody(new byte[1024 * 1024 * 3], ContentType.create("image/png"), "test.png");
//        HttpEntity image = MultipartEntityBuilder.create().addPart("image1", byteArrayBody).addPart("image2", byteArrayBody).addPart("image3", byteArrayBody).build();
//
//     /*
//     * turn Entity to byte[] using ByteArrayOutputStream
//     */
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        image.writeTo(bos);
//        byte[] body = bos.toByteArray();
//
//    /*
//     * add multipart header and body
//     */
//        httpRequest.addHeader(new HTTPHeader("Content-Type", image.getContentType().getValue()));
//        httpRequest.addHeader(new HTTPHeader("messageid", "messageid"));
//        httpRequest.setPayload(body);
//
////        urlFetch.fetch(httpRequest);
//
//        redirectAttrs.addAttribute("key", "a123");
//
//
//        String uploadURL = blobstoreService.createUploadUrl("/upload");
//
//        req.setAttribute("uploadURL", uploadURL);
//        req.setAttribute("file",  new File("E:\\test.png"));
//
//        RequestDispatcher dispatcher =
//                req.getRequestDispatcher("../WEB-INF/templates/upload.jsp");
//        dispatcher.forward(req, resp);
//        return "../WEB-INF/templates/upload.jsp";
        return cardService.findOneCardRandom();
    }


    @RequestMapping(value = "/uriTemplate", method = RequestMethod.GET)
    public String uriTemplate(RedirectAttributes redirectAttrs) {

        redirectAttrs.addAttribute("key", "a123");  // Used as URI template variable
        Date date = new Date();
//        redirectAttrs.addAttribute("date", date);  // Appended as a query parameter
        return "/cards/{account}";
    }

    @RequestMapping(value = "/uriComponentsBuilder", method = RequestMethod.GET)
    public String uriComponentsBuilder() {
        String date = "";
        UriComponents redirectUri = UriComponentsBuilder.fromPath("/redirect/{account}").queryParam("date", date)
                .build().expand("a123").encode();
        return "redirect:" + redirectUri.toUriString();
    }

    @RequestMapping(value = "/{account}", method = RequestMethod.GET)
    public String show(@RequestParam RedirectAttributes key, @PathVariable String account, @RequestParam(required = false) Date date) {
        return "redirect/redirectResults";
    }

    @RequestMapping(method = RequestMethod.GET)
    public
    @ResponseBody
    Eng.CardList getCards() {
        return cardService.findCardList();
    }


    public static void addMultipartBodyToRequest(HttpEntity entity, HTTPRequest req) throws IOException {

    /*
     * turn Entity to byte[] using ByteArrayOutputStream
     */
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        entity.writeTo(bos);
        byte[] body = bos.toByteArray();

    /*
     * extract multipart boundary (body starts with --boundary\r\n)
     */
        String boundary = new BufferedReader(new StringReader(new String(body))).readLine();
        boundary = boundary.substring(2, boundary.length());

    /*
     * add multipart header and body
     */
        req.addHeader(new HTTPHeader("Content-type", "multipart/form-data;boundary=" + boundary));
        req.addHeader(new HTTPHeader("messageid", "messageid"));
        req.setPayload(body);
    }
}