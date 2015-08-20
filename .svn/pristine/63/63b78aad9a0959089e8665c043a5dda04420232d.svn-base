package dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.DynamicRelationshipType;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.rest.graphdb.RestAPI;
import org.neo4j.rest.graphdb.RestGraphDatabase;
import org.neo4j.rest.graphdb.entity.RestNode;
import org.neo4j.rest.graphdb.entity.RestRelationship;
import org.neo4j.rest.graphdb.traversal.RestTraversal;


public class TypeDAO {

	private RestGraphDatabase gdb;

	private RestAPI restAPI;

	private static String SERVER_ROOT_URI = null;

	public TypeDAO() {
		if (SERVER_ROOT_URI == null) {
			try {
				InputStream fis = this.getClass().getClassLoader()
						.getResourceAsStream("mrw.properties");
				Properties pro = new Properties();
				pro.load(fis);
				fis.close();
				String hostName = null;
				String port = null;
				String dbRoot = null;
				if (pro.getProperty("HOSTNAME") != null) {
					hostName = pro.getProperty("HOSTNAME").trim();
				}
				if (pro.getProperty("PORT") != null) {
					port = pro.getProperty("PORT").trim();
				}
				if (pro.getProperty("DBRoot") != null) {
					dbRoot = pro.getProperty("DBRoot").trim();
				}
				SERVER_ROOT_URI = "http://" + hostName + ":" + port + dbRoot;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private Map<String, Object> convertNode2Map(Node node) {
		if (node == null) {
			return null;
		}
		Map<String, Object> data = new HashMap<String, Object>();
		if (node.getPropertyKeys() != null) {
			for (String key : node.getPropertyKeys()) {
				data.put(key, node.getProperty(key));
			}
		}
		data.put(Constants.ObjectIdSuffix.toLowerCase(), node.getId());
		return data;
	}

	/**
	 * 得到node属性
	 * @param objectId
	 * @return
	 */
	public Map<String, Object> getDataById(long objectId) {
		try {
			Node node = this.getRestAPI().getNodeById(objectId);
			if (node == null) {
				return null;
			}
			return this.convertNode2Map(node);
		} catch (Exception ex) {
		}
		return null;
	}

	/**
	 * 	// 获取所有节点  --gaohongtao
	 * @param 
	 * @return
	 */
	public Iterable<Map<String, Object>> getAllNodes() {
		try {
			Iterable<Node> inode = this.getGdb().getAllNodes();
			if (inode == null) {
				return null;
			}
			Set<Map<String, Object>> nSet = new HashSet<Map<String, Object>>();
			for (Node n : inode){
				nSet.add(convertNode2Map(n));
				}
			return nSet;
		} catch (Exception ex) {
		}
		return null;
	}
	
	/**
	 * 根据某节点id的到relationship相关属性
	 * @param objectId   节点id
	 * @param linkTypeNames   关系type
	 * @param isOutgoing      out或in
	 * @return
	 */
	public Set<Map<String, Object>> getDataRelations(long objectId,
			String[] linkTypeNames, boolean isOutgoing) {
		try {
			Set<Relationship> relations = this.getRelationshipsByNodeId(
					objectId, linkTypeNames, isOutgoing);
			Set<Map<String, Object>> relationSet = new HashSet<Map<String, Object>>();
			for (Relationship rel : relations) {
				Map<String, Object> relationMap = new HashMap<String, Object>();
				for (String key : rel.getPropertyKeys()) {
					relationMap.put(key, rel.getProperty(key));
					//System.out.println(rel.getProperty(key));
				}
				relationSet.add(relationMap);
			}
			return relationSet;
		} catch (Exception ex) {
		}
		return null;
	}
	
	/**
	 * 添加节点
	 * @param data 节点属性数组
	 * @return
	 */
	public long createObjectData(Map<String, Object> data) {
		Transaction tx = getGdb().beginTx();
		try {
			if (data.containsKey(Constants.ObjectIdSuffix.toLowerCase())) {
				long objectId = (Long) data.get(Constants.ObjectIdSuffix
						.toLowerCase());
				if (objectId > 0) {
					return -1;
				}
			}
			Node node = getGdb().createNode();
			for (String key : data.keySet()) {
				if (!key.equals(Constants.ObjectIdSuffix.toLowerCase())) {
					try {
						Object val = data.get(key);
						if (val != null) {
							node.setProperty(key, val);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			return node.getId();
		} catch (SecurityException e1) {
			e1.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} finally {
			tx.finish();
		}
		return -1;
	}
	/**
	 * 添加节点间的relation以及relation相关属性
	 * @param startId      开始id
	 * @param endId        末尾id
	 * @param linkTypeName 关系标签
	 * @param props        关系属性
	 * @return
	 */
	public long createObjectLink(long startId, long endId, String linkTypeName,
			Map<String, Object> props) {
		try {
			Node startNode = this.getRestAPI().getNodeById(startId);
			Node endNode = this.getRestAPI().getNodeById(endId);
			RelationshipType linkType = DynamicRelationshipType
					.withName(linkTypeName);
			RestRelationship rRelation = this.getRestAPI().createRelationship(
					startNode, endNode, linkType, props);
			return rRelation.getId();
		} catch (Exception ex) {
		}
		return -1;
	}

	public Long createObject2ObjectTypeLink(long startId, long endId,
			String linkTypeName, Map<String, Object> props) {
		return createObjectLink(startId, endId, linkTypeName, props);
	}

	public Long createObject2ObjectTypeLink(long startId, long endId,
			String linkTypeName) {
		return createObjectLink(startId, endId, linkTypeName, null);
	}

	public Long createObject2PropertyTypeLink(long startId, long endId,
			String linkTypeName) {
		return createObjectLink(startId, endId, linkTypeName, null);
	}

	public Long createObject2PropertyTypeLink(long startId, long endId,
			String linkTypeName, Map<String, Object> props) {
		return createObjectLink(startId, endId, linkTypeName, props);
	}

	/**
	 * 删除relation
	 * @param startId  开始节点Id
	 * @param endId    末尾节点Id
	 * @param linkTypeName   关系type
	 * @return
	 */
	public boolean deleteNodeLink(long startId, long endId, String linkTypeName) {
		try {
			Node node = this.getRestAPI().getNodeById(startId);
			if (node != null) {
				Iterable<Relationship> relations = node.getRelationships(
						DynamicRelationshipType.withName(linkTypeName),
						Direction.OUTGOING);
				if (relations != null) {
					for (Relationship rel : relations) {
						rel.delete();
					}
					return true;
				}
			}
			return false;
		} catch (Exception ex) {
		}
		return false;
	}
	/**
	 * 根据nodeid删除
	 * @param nodeId  id
	 * @return
	 */
	public boolean deleteNodeById(long nodeId) {
		if (nodeId <= 0) {
			return false;
		}
		try {
			Node node = this.getRestAPI().getNodeById(nodeId);
			if (node == null) {
				return false;
			}
			return deleteNode(node);
		} catch (Exception ex) {
		}
		return false;
	}
	//删除node
	private boolean deleteNode(Node node) {
		if (node == null) {
			return false;
		}
		long nodeId = node.getId();
		for (Relationship r : node.getRelationships()) {
			r.delete();
		}
		node.delete();
		try {
			node = this.getRestAPI().getNodeById(nodeId);
		} catch (org.neo4j.graphdb.NotFoundException ex) {
			node = null;
		}
		return node == null;
	}
    //批量删除
	public boolean deleteBranch(long startNodeId, String[] linkTypeNames,
			boolean isOutgoing, int depth) {
		if (startNodeId <= 0) {
			return false;
		}
		if (depth > maxDepth || depth <= 0) {
			depth = maxDepth;
		}
		try {
			Node startNode = this.getRestAPI().getNodeById(startNodeId);
			if (startNode == null) {
				return false;
			}
			Set<Node> branchNodes = this.getBranchNodes(startNode,
					linkTypeNames, isOutgoing, depth);
			boolean isAllDeleted = true;
			for (Node node : branchNodes) {
				isAllDeleted = isAllDeleted && this.deleteNode(node);
			}
			return isAllDeleted;
		} catch (Exception ex) {
		}
		return false;
	}

	//批量查找  
	
	private Map<String, Object> getBranch(Node startNode,
			String[] linkTypeNames, boolean isOutgoing, int depth) {
		if (startNode == null || depth <= 0) {
			return null;
		}
		if (depth > maxDepth) {
			depth = maxDepth;
		}
		Map<String, Object> data = this.convertNode2Map(startNode);
		if (linkTypeNames == null || linkTypeNames.length <= 0) {
			return data;
		}
		for (int i = 0; i < linkTypeNames.length; i++) {
			Set<Map<String, Object>> subSet = null;
			if (!data.containsKey(linkTypeNames[i])) {
				subSet = new HashSet<Map<String, Object>>();
				data.put(linkTypeNames[i], subSet);
			} else {
				subSet = (Set<Map<String, Object>>) data.get(linkTypeNames[i]);
			}
			RelationshipType rt = DynamicRelationshipType
					.withName(linkTypeNames[i]);
			if (rt != null) {
				Iterable<Relationship> relationships = startNode
						.getRelationships(rt, isOutgoing ? Direction.OUTGOING
								: Direction.INCOMING);
				for (Relationship rel : relationships) {
					Map<String, Object> subBranchData = getBranch(
							rel.getEndNode(), linkTypeNames, isOutgoing,
							depth - 1);
					if (subBranchData != null) {
						subSet.add(subBranchData);
					}
				}
			}
		}
		return data;
	}

	
	public Map<String, Object> getBranch(long startNodeId,
			String[] linkTypeNames, boolean isOutgoing, int depth) {
		if (startNodeId <= 0) {
			return null;
		}
		if (depth > maxDepth || depth <= 0) {
			depth = maxDepth;
		}
		try {
			Node startNode = this.getRestAPI().getNodeById(startNodeId);
			if (startNode == null) {
				return null;
			}
			return this.getBranch(startNode, linkTypeNames, isOutgoing, depth);
		} catch (Exception ex) {
		}
		return null;
	}

	private Set<Node> getBranchNodes(Node startNode, String[] linkTypeNames,
			boolean isOutgoing, int depth) {
		if (startNode == null || depth <= 0) {
			return null;
		}
		if (depth > maxDepth) {
			depth = maxDepth;
		}
		Set<Node> branchNodes = new HashSet<Node>();
		branchNodes.add(startNode);
		if (linkTypeNames == null || linkTypeNames.length <= 0) {
			return branchNodes;
		}
		for (int i = 0; i < linkTypeNames.length; i++) {
			RelationshipType rt = DynamicRelationshipType
					.withName(linkTypeNames[i]);
			if (rt != null) {
				Iterable<Relationship> relationships = startNode
						.getRelationships(rt, isOutgoing ? Direction.OUTGOING
								: Direction.INCOMING);
				for (Relationship rel : relationships) {
					Set<Node> subBranch = getBranchNodes(rel.getEndNode(),
							linkTypeNames, isOutgoing, depth - 1);
					if (subBranch != null) {
						branchNodes.addAll(subBranch);
					}
				}
			}
		}
		return branchNodes;
	}

	int maxDepth = 20;

	public Set<Map<String, Object>> getBranchNodes(long startNodeId,
			String[] linkTypeNames, boolean isOutgoing, int depth) {
		if (startNodeId <= 0) {
			return null;
		}
		if (depth > maxDepth || depth <= 0) {
			depth = maxDepth;
		}
		try {
			Node startNode = this.getRestAPI().getNodeById(startNodeId);
			if (startNode == null) {
				return null;
			}
			Set<Node> branchNodes = getBranchNodes(startNode, linkTypeNames,
					isOutgoing, depth);
			Set<Map<String, Object>> dataSet = new HashSet<Map<String, Object>>();
			for (Node node : branchNodes) {
				Map<String, Object> data = this.convertNode2Map(node);
				if (data != null) {
					dataSet.add(data);
				}
			}
			return dataSet;
		} catch (Exception ex) {
		}
		return null;
	}

	Relationship getRelationsById(long relationId) {
		Relationship relationship = this.getRestAPI().getRelationshipById(
				relationId);
		return relationship;
	}

	public Set<Relationship> getRelationshipsByNodeId(long nodeId,
			String[] linkTypeNames, boolean isOutgoing) {
		Node node = this.getRestAPI().getNodeById(nodeId);
		Set<Relationship> relations = new HashSet<Relationship>();
		for (int i = 0; i < linkTypeNames.length; i++) {
			RelationshipType rt = DynamicRelationshipType
					.withName(linkTypeNames[i]);
			if (rt != null) {
				Iterable<Relationship> relationships = node.getRelationships(
						rt, isOutgoing ? Direction.OUTGOING
								: Direction.INCOMING);
				for (Relationship rel : relationships) {
					relations.add(rel);
				}
			}
		}
		return relations;
	}

	private boolean deleteLinkById(long linkId) {
		if (linkId <= 0) {
			return false;
		}
		Relationship rel = this.getRestAPI().getRelationshipById(linkId);
		if (rel == null) {
			return false;
		}
		rel.delete();
		rel = this.getRestAPI().getRelationshipById(linkId);
		return rel == null;
	}

	public boolean deleteObject2PropertyTypeLinkById(long linkId) {
		return deleteLinkById(linkId);
	}

	public boolean deleteObject2ObjectTypeLinkById(long linkId) {
		return deleteLinkById(linkId);
	}

	public RestGraphDatabase getGdb() {
		if (gdb == null) {
			gdb = new RestGraphDatabase(SERVER_ROOT_URI);
		}
		return gdb;
	}

	public RestAPI getRestAPI() {
		if (restAPI == null) {
			restAPI = this.getGdb().getRestAPI();
		}
		return restAPI;
	}
    /**
     * 更改节点属性值
     * @param data
     * @return
     */
	public boolean updateObjectData(Map<String, Object> data) {
		if (data.containsKey(Constants.ObjectIdSuffix.toLowerCase())) {
			Transaction tx = getGdb().beginTx();
			try {
				long objectId = (Long) data.get(Constants.ObjectIdSuffix
						.toLowerCase());
				if (objectId <= 0) {
					return false;
				}
				Node node = getGdb().getNodeById(objectId);
				for (String key : data.keySet()) {
					if (!key.equals(Constants.ObjectIdSuffix.toLowerCase())) {
						try {
							Object val = data.get(key);
							if (val != null) {
								node.setProperty(key, val);
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				return true;
			} catch (SecurityException e1) {
				e1.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} finally {
				tx.finish();
			}
		}
		return false;
	}
    //遍历
	/**
	 * 遍历
	 * @param startNodeId
	 * @param isDepthFirst
	 * @param relationName
	 * @param direction
	 * @param depth
	 * @return
	 */
	public Iterable<Node> traverse(long startNodeId, boolean isDepthFirst,
			String relationName, Direction direction, int depth) {
		try {
			RestNode node = this.getRestAPI().getNodeById(startNodeId);

			RestTraversal restTraversal = new RestTraversal();
			if (!isDepthFirst) {
				restTraversal.breadthFirst();
			}

			RelationshipType relationshipType = DynamicRelationshipType
					.withName(relationName);
			restTraversal.relationships(relationshipType, direction);
			restTraversal.maxDepth(depth);
			Map<String, Object> description = new HashMap<String, Object>();
			description = restTraversal.getPostData();

			Iterable<Node> nodes = this.getRestAPI()
					.traverse(node, description).nodes();
			return nodes;
		} catch (Exception ex) {
		}
		return null;
	}

	public Map<?, ?> query(String statement, Map<String, Object> params) {

		return this.getRestAPI().query(statement, params);
	}

}
