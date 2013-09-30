package com.zy17.controller;

import com.google.protobuf.InvalidProtocolBufferException;
import com.zy17.domain.TextMessage;
import com.zy17.protobuf.domain.AddressBookProtos;
import com.zy17.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: yan.zhang
 * Date: 13-9-17
 * Time: 上午11:20
 */
@Controller
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    /**
     * 创建消息
     *
     * @param message
     * @return
     */
    @ResponseStatus(value = HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST)
    public
    @ResponseBody
    TextMessage createMessge(@RequestBody @Valid TextMessage message) {
        messageService.add(message);

        return message;
    }


    /**
     * 删除消息
     *
     * @return
     */
    @ResponseStatus(value = HttpStatus.CREATED)
    @RequestMapping(value = "/{messageid}", method = RequestMethod.DELETE)
    public
    @ResponseBody
    void deleteMessages(@PathVariable(value = "messageid") String messageid) {
        messageService.delete(messageid);
    }


    /**
     * 获取具体消息
     *
     * @return
     */

    @RequestMapping(value = "/{messageid}", method = RequestMethod.GET)
    public
    @ResponseBody
    TextMessage getMessage(@PathVariable(value = "messageid") String messageid) {
        return messageService.getMessage(messageid);
    }


    /**
     * 获取某个消息
     *
     * @return
     */

    @RequestMapping(method = RequestMethod.GET)
    public
    @ResponseBody
    List<TextMessage> getMessages() {
        return messageService.getAllMessage();
    }


    /**
     * 获取所有消息
     *
     * @return
     */
    @RequestMapping(value = "/personbytes", method = RequestMethod.GET)
    public
    @ResponseBody
    List<byte[]> getPersonsBytes() {
        return messageService.findAllPersonBytes();
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "/person", method = RequestMethod.GET)
    public
    @ResponseBody
    AddressBookProtos.PersonList getPersons() {
        return messageService.findPersonList();
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @RequestMapping(value = "/person",method = RequestMethod.POST)
    public
    @ResponseBody
    void createPerson(@RequestBody AddressBookProtos.Person person) throws InvalidProtocolBufferException {
        this.messageService.add(person);
    }


//    @RequestMapping("connected")
//    public void connected(@RequestBody UseClientInfo useClientInfo, HttpServletRequest request, HttpServletResponse response) throws IOException {
//        log.info(useClientInfo);
//    }
//
//    @ResponseBody
//    @RequestMapping("get_useclientinfo")
//    public UseClientInfo getUseClientInfo(HttpServletResponse response) throws IOException{
//        UseClientInfo.Builder useClientInfoBuilder = UseClientInfo.newBuilder();
//        return useClientInfoBuilder.build();
//    }


}
