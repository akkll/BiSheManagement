package com.zyq.service;

import com.zyq.domain.manager.Manager;

/**
 * @author MagicBOOK
 * @version 1.0
 * @className ManagerService
 * @date 2023-02-25 16:58
 */
public interface ManagerService {
    Manager getManagerByName(String name, String password, int iden);
}
