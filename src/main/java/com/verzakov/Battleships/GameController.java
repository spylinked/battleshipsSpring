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
    public String Start(@RequestParam String p1name, @RequestParam String p2name, Model model){
        //game.addPlayer(new Bot(p1name));
        game.addPlayer(new Human(p1name));
        game.getPlayersList().get(0).placeShips();
        game.addPlayer(new Human(p2name));
        game.getPlayersList().get(1).placeShips();

        model.addAttribute("p1name",game.getPlayersList().get(0).getName());
        model.addAttribute("p2name",game.getPlayersList().get(1).getName());

        Collections.shuffle(game.getPlayersList());

        //game.getPlayersList().get(0)
        model.addAttribute("myGrid",game.getPlayersList().get(0).getField().getCellGrid());
        model.addAttribute("rivalGrid",game.getPlayersList().get(1).getField().getCellGrid());
        model.addAttribute("ships",game.getPlayersList().get(0).getField().getShipsList());
        return "start";
    }

    @GetMapping(value = "/shoot")
    public String Shoot(@RequestParam int x, @RequestParam int y, Model model){
        //game.addPlayer(new Bot(p1name));
        game.getPlayersList().get(1).getField().shoot(x,y);
        model.addAttribute("rivalGrid",game.getPlayersList().get(1).getField().getCellGrid());
        return "rivalField";
    }

    @GetMapping(value = "/status")
    @ResponseBody
    public String GetLastShotStatus(){
        return game.getPlayersList().get(1).getField().getLastShotStatus();
    }

    @GetMapping(value = "/giveTurn")
    public String giveTurn(Model model){
        model.addAttribute("name",game.getPlayersList().get(1).getName());
        return "taketurn";
    }

    @GetMapping(value = "/takeTurn")
    public String takeTurn(Model model){
        model.addAttribute("p1name",game.getPlayersList().get(0).getName());
        model.addAttribute("p2name",game.getPlayersList().get(1).getName());

        Collections.swap(game.getPlayersList(), 0, 1);

        model.addAttribute("myGrid",game.getPlayersList().get(0).getField().getCellGrid());
        model.addAttribute("rivalGrid",game.getPlayersList().get(1).getField().getCellGrid());
        model.addAttribute("ships",game.getPlayersList().get(0).getField().getShipsList());
        return "start";
    }

}


