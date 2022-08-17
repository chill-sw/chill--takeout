package com.chill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chill.common.Result;
import com.chill.entity.AddressBook;


public interface AddressBookService extends IService<AddressBook> {

    Result saveAddressBook(AddressBook addressBook);

    Result setDefault(AddressBook addressBook);

    Result getByAddressId(Long id);

    Result getDefault();

    Result listAddressBook(AddressBook addressBook);
}
