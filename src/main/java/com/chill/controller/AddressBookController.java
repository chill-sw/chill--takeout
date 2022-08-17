package com.chill.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.chill.common.Result;
import com.chill.entity.AddressBook;
import com.chill.service.AddressBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 地址簿管理
 */
@Slf4j
@RestController
@RequestMapping("/addressBook")
public class AddressBookController {

    @Autowired
    private AddressBookService addressBookService;

    /**
     * 新增
     */
    @PostMapping
    public Result saveAddressBook(@RequestBody AddressBook addressBook) {
        return addressBookService.saveAddressBook(addressBook);
    }

    /**
     * 设置默认地址
     */
    @PutMapping("default")
    public Result setDefault(@RequestBody AddressBook addressBook) {
        return addressBookService.setDefault(addressBook);
    }

    /**
     * 根据id查询地址
     */
    @GetMapping("/{id}")
    public Result getByAddressId(@PathVariable Long id) {

       return addressBookService.getByAddressId(id);
    }

    /**
     * 查询默认地址
     */
    @GetMapping("default")
    public Result getDefault() {
       return addressBookService.getDefault();
    }

    /**
     * 查询指定用户的全部地址
     */
    @GetMapping("/list")
    public Result listAddressBook(AddressBook addressBook) {
        return addressBookService.listAddressBook(addressBook);
    }
}
