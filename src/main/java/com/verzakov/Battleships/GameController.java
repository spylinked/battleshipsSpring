package com.verzakov.Battleships;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/game")
public class GameController {
    Game game;
    @GetMapping
    public String Game(){
        game = new Game();
        return "game";
    }
    @GetMapping("/start")
    public String Start(@RequestParam String p1name, @RequestParam String p2name, @RequestParam Boolean p1bot, @RequestParam Boolean p2bot, Model model){
        //game.addPlayer(new Bot(p1name));
        if(p1bot)
            game.addPlayer(new Bot(p1name));
        else
            game.addPlayer(new Human(p1name));
        game.getPlayersList().get(0).placeShips();
        if(p2bot)
            game.addPlayer(new Bot(p2name));
        else
            game.addPlayer(new Human(p2name));
        game.getPlayersList().get(1).placeShips();

        Collections.shuffle(game.getPlayersList());

        System.out.println(game.getPlayersList().get(0).getClass().getSimpleName());
        System.out.println(game.getPlayersList().get(1).getClass().getSimpleName());

        if(game.getPlayersList().get(0).getClass().getSimpleName().equals("Bot")) {
            game.getPlayersList().get(0).shoot(game.getPlayersList().get(1));
            Collections.shuffle(game.getPlayersList());
        }

        //game.getPlayersList().get(0)
        model.addAttribute("name",game.getPlayersList().get(0).getName());
        model.addAttribute("myGrid",game.getPlayersList().get(0).getField().getCellGrid());
        model.addAttribute("rivalGrid",game.getPlayersList().get(1).getField().getCellGrid());
        model.addAttribute("ships",game.getPlayersList().get(0).getField().getShipsList());
        return "start";
    }

    @GetMapping(value = "/shoot")
    public String Shoot(@RequestParam int x, @RequestParam int y, Model model){
        //game.addPlayer(new Bot(p1name));
        if(game.getLastShotStatus() != 0 && game.getLastShotStatus() != 2) {
            game.setLastShotStatus(game.getPlayersList().get(1).getField().shoot(x, y));
        }
        model.addAttribute("rivalGrid",game.getPlayersList().get(1).getField().getCellGrid());
        return "rivalField";
    }

    @GetMapping(value = "/status")
    @ResponseBody
    public String GetLastShotStatus(){
        //return "{\""+game.getLastShotStatus()+"\":\"" + game.getPlayersList().get(1).getClass().getSimpleName() +"\"}";
        return "{\"status\":"+game.getLastShotStatus()+", \"next\":\"" + game.getPlayersList().get(1).getClass().getSimpleName() + "\"}";
    }

    @GetMapping(value = "/giveTurn")
    public String giveTurn(Model model){
        model.addAttribute("name",game.getPlayersList().get(1).getName());
        return "taketurn";
    }

    @GetMapping(value = "/takeTurn")
    public String takeTurn(Model model){
        game.setLastShotStatus(-1);

        Collections.swap(game.getPlayersList(), 0, 1);
        model.addAttribute("name",game.getPlayersList().get(0).getName());
        model.addAttribute("myGrid",game.getPlayersList().get(0).getField().getCellGrid());
        model.addAttribute("rivalGrid",game.getPlayersList().get(1).getField().getCellGrid());
        model.addAttribute("ships",game.getPlayersList().get(0).getField().getShipsList());
        return "start";
    }
    @GetMapping(value = "/botTurn")
    public String botTurn(Model model){
        game.setLastShotStatus(-1);

        Collections.swap(game.getPlayersList(), 0, 1);
        //game.setLastShotStatus(game.getPlayersList().get(0).shoot(game.getPlayersList().get(1)));
        game.getPlayersList().get(0).shoot(game.getPlayersList().get(1));
        Collections.swap(game.getPlayersList(), 0, 1);
        model.addAttribute("name",game.getPlayersList().get(0).getName());
        model.addAttribute("myGrid",game.getPlayersList().get(0).getField().getCellGrid());
        model.addAttribute("rivalGrid",game.getPlayersList().get(1).getField().getCellGrid());
        model.addAttribute("ships",game.getPlayersList().get(0).getField().getShipsList());
        return "start";
    }

}


