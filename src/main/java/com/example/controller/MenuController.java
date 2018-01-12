package com.example.controller;


import com.example.Domain.Menu_card;
import com.example.Domain.Menucardelasticsearch;
import com.example.Service.MenuService;
import com.example.repository.MenuRepositoryelastic;
import com.example.repository.MenuRespository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/menu")

@Api(value = "MENU CARD ",description = "Restaurant Menu card All Operations")
public class MenuController {

    private static final Logger LOGGER=(Logger) LoggerFactory.getLogger(MenuController.class);


    @Autowired
    private MenuService menuService;

    @Autowired
    private MenuRespository menuRespository;
    @Autowired
    private MenuRepositoryelastic menuRepositoryelastic;

    ModelMapper modelMapper = new ModelMapper();




    @RequestMapping(method = RequestMethod.GET,path = "/items")
    //@GetMapping()
    @ApiOperation(value = "Display all items present in my Restaurant")

    @ApiResponses(
            value ={

                    @ApiResponse(code=200,message = "successful response gives"),
                    @ApiResponse(code=400,message = "not successful response gives"),
                    @ApiResponse(code=401,message = "Unauthorized response gives"),
                    @ApiResponse(code=403,message = "forbidden response gives "),
                    @ApiResponse(code=403,message = "NOT FOUND response gives "),
            }
    )


        public List<Menucardelasticsearch> getAllItems () {
        try {
            List<Menucardelasticsearch> list = menuService.getAllItems();
            LOGGER.info("run succesfull");
            return list;
        } catch (Exception E) {
            LOGGER.error("somehing going wrong in get method");
            return null;
        }
    }

   // @RequestMapping(method = RequestMethod.POST,path="/items")
   // public void addItemToMenu(@RequestBody Menu_card menu_card){
    //    menuService.addItemToMenu(menu_card);
    //}


   @RequestMapping(method = RequestMethod.POST,path="/items")
    //@PostMapping("/items")
   @ApiOperation(value = "enter new element in menucard")
   @ApiResponses(
           value ={

                   @ApiResponse(code=200,message = "successful response gives"),
                   @ApiResponse(code=400,message = "not successful response gives"),
                   @ApiResponse(code=401,message = "Unauthorized response gives"),
                   @ApiResponse(code=403,message = "forbidden response gives "),
                   @ApiResponse(code=403,message = "NOT FOUND response gives "),
           }
   )


   public void addItemToMenu(@RequestBody Menu_card menu_card) {
       try {
           LOGGER.info("successfully added new item");
           menuRespository.save(menu_card);
           Menucardelasticsearch menucardelasticsearch = modelMapper.map(menu_card, Menucardelasticsearch.class);
           menuRepositoryelastic.save(menucardelasticsearch);
       } catch (Exception exception) {
           LOGGER.error("somehing going wrong in post method");
       }
   }

       //@DeleteMapping("/items/{id}")
       @ApiOperation(value = "Delete items  by present in my menucard")
    @RequestMapping(value = "/items/{id}", method = RequestMethod.DELETE)
    void delete(@PathVariable("id") Integer id) {
        try{
            LOGGER.info("successfully deleted a item");
            menuService.delete(id);

        }
        catch (Exception e){
            LOGGER.error("somehing going wrong in delete method");
        }


    }

     //@PutMapping("/items/{id}")

    @RequestMapping(value = "/items/{id}",method = RequestMethod.PUT)
    @ApiOperation(value = "Update items list resent in menu card")
    void update(@RequestBody Menu_card menu_card) {
        try {

            menuService.update(menu_card);
        } catch (Exception e) {
            LOGGER.error("error in updating ");
        }
    }


    //@GetMapping("/items/{id")
   @RequestMapping(value = "/items/{id}",method = RequestMethod.GET)
   @ApiOperation(value = "Get  items  by id present in my menucard")
   @ApiResponses(
           value ={

                   @ApiResponse(code=200,message = "successful response gives"),
                   @ApiResponse(code=400,message = "not successful response gives"),
                   @ApiResponse(code=401,message = "Unauthorized response gives"),
                   @ApiResponse(code=403,message = "forbidden response gives "),
                   @ApiResponse(code=403,message = "NOT FOUND response gives "),
           }
   )

   public Menucardelasticsearch findById(@PathVariable("id") int id) {
       try {
           LOGGER.info("find succesfully a item bu id");
           return menuService.findById(id);
       } catch (Exception e) {
           LOGGER.error("error during finding data by id");
           return null;
       }
   }

    // @GetMapping("/items/{min}/{max}")
    @RequestMapping(method = RequestMethod.GET,value="/items/{min}/{max}")
    @ApiOperation(value = "get items list by price range in menu card")
    @ApiResponses(
            value ={

                    @ApiResponse(code=200,message = "successful response gives"),
                    @ApiResponse(code=400,message = "not successful response gives"),
                    @ApiResponse(code=401,message = "Unauthorized response gives"),
                    @ApiResponse(code=403,message = "forbidden response gives "),
                    @ApiResponse(code=403,message = "NOT FOUND response gives "),
            }
    )

    public List<Menucardelasticsearch> findBetween(@PathVariable int min,@PathVariable int max) {
        //List<Menucardelasticsearch> arrayList=new ArrayList<Menucardelasticsearch>();
        //arrayList.addAll(menuRepositoryelastic.findMenucardelasticsearchByPrice(min,max));
        //menuRepositoryelastic.findByPriceBetween(min,max).forEach(arrayList::add);
        try{
            LOGGER.info("succesfully find value between range");
            return menuService.findBetween(min,max);
        }
        catch (Exception e){
            LOGGER.error("error during finding value in range");
            return null;
        }
    }

}












