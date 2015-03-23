package com.neo4j.query.test;

import java.util.Iterator;

import org.neo4j.cypher.javacompat.ExecutionEngine;
import org.neo4j.cypher.javacompat.ExecutionResult;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.helpers.collection.IteratorUtil;

public class Main {

	public static void main(String[] args) {
		
		GraphDatabaseFactory graphDbFactory = new GraphDatabaseFactory();
		GraphDatabaseService graphDb = graphDbFactory.newEmbeddedDatabase("C:\\Zakaria\\NeoTests\\Tetralecture");
		
		ExecutionEngine execEngine = new ExecutionEngine(graphDb);
		//ExecutionResult execResult = execEngine.execute("MATCH (auteur:AUTEUR) WHERE auteur.Nom_Court='MESGUIC' RETURN auteur");
		try (Transaction ignored = graphDb.beginTx()) {
			ExecutionResult execResult = execEngine.execute("MATCH (auteur:AUTEUR) WHERE auteur.Nom_Court='MESGUIC' RETURN auteur");
			Iterator<Node> aut_column = execResult.columnAs("auteur");
			for(Node node : IteratorUtil.asIterable(aut_column)) {
				String nodeResult = node + " : " + node.getProperty("Nom_Court");
				System.out.println(nodeResult);
			}
//			String results = execResult.dumpToString();
//			System.out.println(results);
		}

	}

}
