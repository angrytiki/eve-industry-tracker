package controllers;

import java.util.List;

import model.WalletJournal;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;

import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

public class Wallet extends Controller {
	@BodyParser.Of(BodyParser.Json.class)
	public static Result getTransactions() {
		ObjectNode result = Json.newObject();
		result.put("message", "Not yet implemented");
		return ok(result);
	}
	
	@BodyParser.Of(BodyParser.Json.class)
	public static Result getTotals() {
		ObjectNode result = Json.newObject();
		result.put("message", "Not yet implemented");
		return ok(result);
	}
	
	@BodyParser.Of(BodyParser.Json.class)
	public static Result getJournals() {
		List<WalletJournal> journals = WalletJournal.all();
		JsonNode result = Json.toJson(journals);
		return ok(result);
	}
}
