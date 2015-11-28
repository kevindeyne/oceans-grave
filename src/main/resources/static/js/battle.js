
$(function(){		
	var roundManager = new RoundManager();
	
	
});
	
/**
 * @name RoundManager, manages the rounds of the combat
 * @author Kevin Deyne
 *
 * Basic usage:
 * var someFunction = new SomeFunction();
 * someFunction.init();
 *
 * additionally you can use methods like someFunction.methodName();
 *
 * Advanced usage:
 * var someFunction = new SomeFunction({
 *      "additionalOption": "thatCanOvervriteDefaults"
 * });
 */
function RoundManager(opts) {
    //assign _root and config private variables
    var _root = this;
    var _cfg = $.extend({
        "skillButtons": $(".skill")
    }, opts);

    _bindUIActions = function() {
		//bind actions to skill buttons
        _cfg.skillButtons.click(function (){   
        	if(!$(this).hasClass("disabled")){
	        	_markRoundAsEnemyTurn();
	        	
	        	var abilityId = $(this).attr("id");
	    		$.getJSON( abilityURL.replace(abilityParam, abilityId), function( data ) {
	    			_abilityEffect(data);
	    			_root.enemyRound(data);
	    		});
        	}
		});
    }
    
    _markRoundAsEnemyTurn = function() {
    	_cfg.skillButtons.addClass("disabled");
    	$("#round-info").text(messages.turnEnemy);
    }
    
    _markRoundAsPlayerTurn = function() {
    	_cfg.skillButtons.removeClass("disabled");
    	$("#round-info").text(messages.turnPlayer);
    }
    
    _abilityEffect = function(data) {
    	_editActorHP("#enemy-info", parseInt(data.enemyHPChange), false);
    	_editActorHP("#player-info", parseInt(data.playerHPChange), false);
    }
    
	_editActorHP = function(divID, hpChange, enemyRound) {
		if(0 != hpChange){
			var currentHP = parseInt($(divID + " .current-health").text());
			var maxHP = parseInt($(divID + " .fully-health").text().replace('/', '').trim());
			currentHP += hpChange;
			
			var actor = "Enemy";
			if(-1 !== divID.indexOf("player")){ actor = "Player"; }
			
			var healed = "healed";
			if(hpChange < 0){ healed = "hit"; }
			
			if(enemyRound){
				actor = "Enemy round: " + actor;
			}
			
			_writeBattleLog(actor + " got " + healed + " for " + Math.abs(hpChange) + " points");
			
			if(currentHP < 0){ currentHP = 0; }		
			if(currentHP > maxHP ){ currentHP = maxHP; }
			
			$(divID + " .current-health").text(currentHP);
		}
	}
	
	
	_writeBattleLog = function(log) {
		var paragraph = $("<p />").text(log);
		$("#battle-log").prepend(paragraph);
    }
		
	//checks if any of the actors has reached 0 health
	_checkIfBattleIsOver = function() {
		var isOver = false;
		$(".current-health").each(function(){
			if(0 == parseInt($(this).text())){
				isOver = true;
			}
		});
        return isOver;
    }
	
	_battleOverSequence = function() {
        alert("done!");
    }
	
    /* Some Public Method*/
    this.enemyRound = function(data) {
		var isOver = _checkIfBattleIsOver();
		if(!isOver){
			setTimeout(function(){						
		    	_editActorHP("#enemy-info", parseInt(data.enemyturn_enemyHPChange), true);
		    	_editActorHP("#player-info", parseInt(data.enemyturn_playerHPChange), true);
		    	
				if(_checkIfBattleIsOver()){
					_battleOverSequence();
				} else {
					_markRoundAsPlayerTurn();
				}
			}, 1100);
		} else {
			_battleOverSequence();
		}		
    }
    
    /* INITIALIZE */
    var init = function() {
      _bindUIActions();
      return _root;
    }
    
    return init();
}








/**
 * @name exampleStructure
 * @author
 *
 * Basic usage:
 * var someFunction = new SomeFunction();
 * someFunction.init();
 *
 * additionally you can use methods like someFunction.methodName();
 *
 * Advanced usage:
 * var someFunction = new SomeFunction({
 *      "additionalOption": "thatCanOvervriteDefaults"
 * });
 */
function exampleStructure(opts) {
    //assign _root and config private variables
    var _root = this;
    var _cfg = $.extend({
        "someOption":  "some value"
    }, opts);

    /*
        Some Private Method (no "this")
    */
    _somePrivateMethod = function() {
        //some code
        console.log("_somePrivateMethod");
    }
    /*
        Some Public Method
    */
    this.somePublicMethod = function() {
        //some code
        console.log("somePublicMethod");
    }
    
    /*
        INITIALIZE
    */
    var init = function() {
        //some code
        _somePrivateMethod();
        _root.somePublicMethod();
      return _root;
    }
    
    return init();
}