package cn.linjk.jarvis.common.test.common;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;
import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * @author linjk
 *
 */
public class TestJexlRuleCompute {
	@Test
	public void testJexlTrue() {
		String rule = "(R1||R2)&&R3";
		Map<String, Boolean> maps = new HashMap<>();
		maps.put("R1", Boolean.TRUE);
		maps.put("R2", Boolean.FALSE);
		maps.put("R3", Boolean.TRUE);
		JexlEngine engine = new JexlEngine();
		Expression expression = engine.createExpression(rule);
		JexlContext context = new MapContext();
		maps.keySet().forEach(key -> context.set(key, maps.get(key)));
		Assert.assertTrue((Boolean)expression.evaluate(context));
	}
	
	@Test
	public void testJexlFalse() {
		String rule = "(R1||R2)&&R3";
		Map<String, Boolean> maps = new HashMap<>();
		maps.put("R1", Boolean.TRUE);
		maps.put("R2", Boolean.FALSE);
		maps.put("R3", Boolean.FALSE);
		JexlEngine engine = new JexlEngine();
		Expression expression = engine.createExpression(rule);
		JexlContext context = new MapContext();
		maps.keySet().forEach(key -> context.set(key, maps.get(key)));
		Assert.assertFalse((Boolean)expression.evaluate(context));
	}
}
