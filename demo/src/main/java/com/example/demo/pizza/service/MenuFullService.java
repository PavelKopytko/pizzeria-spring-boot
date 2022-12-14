package com.example.demo.pizza.service;

import com.example.demo.pizza.core.dto.MenuDto;
import com.example.demo.pizza.core.dto.MenuRowDto;
import com.example.demo.pizza.core.entity.Menu;
import com.example.demo.pizza.core.entity.MenuRow;
import com.example.demo.pizza.core.entity.api.IMenuRow;
import com.example.demo.pizza.core.entity.api.IPizzaInfo;
import com.example.demo.pizza.dao.IMenuFullDao;
import com.example.demo.pizza.service.api.IMenuFullService;
import com.example.demo.pizza.service.api.IPizzaInfoService;
import com.example.demo.pizza.service.exception.IDServiceException;
import com.example.demo.pizza.service.exception.NotUniqServiceException;
import com.example.demo.pizza.service.exception.ServiceException;
import com.example.demo.pizza.service.exception.ValidateException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional(readOnly = true)
public class MenuFullService implements IMenuFullService {

    private final IMenuFullDao menuDao;
    private final IPizzaInfoService pizzaInfoService;


    public MenuFullService(IMenuFullDao menuDao, IPizzaInfoService pizzaInfoService) {
        this.menuDao = menuDao;
        this.pizzaInfoService = pizzaInfoService;
    }

    @Override
    @Transactional
    public Menu create(MenuDto item) throws ServiceException, ValidateException, NotUniqServiceException {

        Menu menuOut = null;

        try {
            validate(item);
            List<IMenuRow> menuRows = new ArrayList<>();
            for (MenuRowDto row : item.getItems()) {
                IPizzaInfo pizzaInfo = pizzaInfoService.read(row.getInfoId());
                menuRows.add(new MenuRow(
                                LocalDateTime.now(),
                                LocalDateTime.now(),
                                pizzaInfo,
                                row.getPrice()
                        )
                );
            }
            Menu menu = new Menu(
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    item.getName(),
                    item.isEnable(),
                    menuRows
            );
            menuOut = menuDao.save(menu);

        } catch (IllegalStateException | IllegalArgumentException e) {
            throw new ValidateException(e.getMessage(), e);
//        } catch (DaoException e) {
//            throw new ServiceException(e.getMessage(), e);
//        } catch (NotUniqDaoException e) {
//            throw new NotUniqServiceException(e.getMessage(), e);
        } catch (IDServiceException e) {
            throw new RuntimeException(e);
        }

        return menuOut;
    }

    @Override
    public Menu read(long id) throws IDServiceException, ServiceException {

        Menu menu = null;
        try {
            menu = menuDao.findById(id).orElseThrow();
        } catch (NoSuchElementException e) {
            throw new IDServiceException("Menu is not found");
        }
//        if (menu == null) {
//            throw new IDServiceException("Меню не найдено");
//        }
//        } catch (DaoException e) {
//            throw new ServiceException(e.getMessage(), e);
//        }

        return menu;
    }

    @Override
    public List<Menu> get() throws ServiceException {
        List<Menu> menus = null;
//        try {

        menus = menuDao.findAll();
//        if (menus.isEmpty()) {
//            throw new IllegalArgumentException("Нет доступных меню");
//        }

//        } catch (DaoException e) {
//            throw new ServiceException(e.getMessage(), e);
//        }
        return menus;
    }

    @Override
    @Transactional
    public Menu update(long id, LocalDateTime dtUpdate, MenuDto item) throws ServiceException, ValidateException, NotUniqServiceException {

        Menu menu = null;

        try {
            validate(item);

            Menu readed = menuDao.getReferenceById(id);

            if (readed == null) {
                throw new IllegalArgumentException("Меню не найдено");
            }
            if (!readed.getDtUpdate().isEqual(dtUpdate)) {
                throw new IllegalArgumentException("К сожалению меню уже было отредактировано кем-то другим");
            }

            List<IMenuRow> menuRows = new ArrayList<>();
            for (MenuRowDto row : item.getItems()) {

                IPizzaInfo pizzaInfo = pizzaInfoService.read(row.getInfoId());

                menuRows.add(new MenuRow(
                                LocalDateTime.now(),
                                LocalDateTime.now(),
                                pizzaInfo,
                                row.getPrice()
                        )
                );
            }

//            readed.setDtUpdate(LocalDateTime.now());

            readed.setName(item.getName());
            readed.setEnable(item.isEnable());
            readed.setItems(menuRows);
//
//
            menu = menuDao.save(readed);


//        } catch (IllegalStateException | IllegalArgumentException e) {
//            throw new ValidateException(e.getMessage(), e);
//        } catch (DaoException e) {
//            throw new ServiceException(e.getMessage(), e);
//        } catch (NotUniqDaoException e) {
//            throw new NotUniqServiceException(e.getMessage(), e);
        } catch (IDServiceException e) {
            throw new RuntimeException(e);
        }
        return menu;
    }

    @Override
    @Transactional
    public void delete(long id, LocalDateTime dtUpdate) throws ServiceException {

        try {
            this.menuDao.delete(read(id));
//        } catch (DaoException e) {
//            throw new ServiceException(e.getMessage(), e);
        } catch (IDServiceException e) {
            throw new RuntimeException(e);
        }
    }

    private void validate(MenuDto item) {
        if (item == null) {
            throw new IllegalStateException("Вы не передали меню");
        }
        if (item.getName() == null || item.getName().isBlank()) {
            throw new IllegalArgumentException("Вы не заполнили название меню");
        }
        if (item.getItems() == null || item.getItems().isEmpty()) {
            throw new IllegalArgumentException("Вы не заполнили строки меню");
        }
    }
}
