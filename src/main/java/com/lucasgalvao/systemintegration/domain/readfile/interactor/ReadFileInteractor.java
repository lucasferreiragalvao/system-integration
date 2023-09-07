package com.lucasgalvao.systemintegration.domain.readfile.interactor;

import com.lucasgalvao.systemintegration.domain.readfile.entity.userorder.UserOrderEntity;

import java.io.FileNotFoundException;
import java.util.List;

public interface ReadFileInteractor {
    List<UserOrderEntity> execute(String dir) throws FileNotFoundException;
}
