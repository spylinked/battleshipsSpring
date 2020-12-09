package com.verzakov.Battleships;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        game.addPlayer(new Human(p2name));
        //game.getPlayersList().get(0).placeShips();
        model.addAttribute("p1name",game.getPlayersList().get(0).getName());
        model.addAttribute("p2name",game.getPlayersList().get(1).getName());
        //game.getPlayersList().get(0)
        model.addAttribute("cellGrid",game.getPlayersList().get(0).getField().getCellGrid());
        model.addAttribute("ships",game.getPlayersList().get(0).getField().getShipsList());
        return "start";
    }

}
