package org.homi.plugins.scriptingengine.specification;

import static org.homi.plugin.specification.Constraints.*;
import static org.homi.plugin.specification.SpecificationHelper.defineType;
import static org.homi.plugin.specification.SpecificationHelper.processType;
import static org.homi.plugin.specification.SpecificationHelper.processTypes;

import java.util.List;
import java.util.Map;

import org.homi.plugin.specification.ISpecification;
import org.homi.plugin.specification.SpecificationID;
import org.homi.plugin.specification.types.TypeDef;

class Types {
	public static TypeDef<?> ACTION_NAME= defineType(String.class, notNull(), minLength(1)); 
	public static TypeDef<?> ARGUMENTS= defineType(Map.class, notNull()); 
	public static TypeDef<?> SCRIPT= defineType(String.class, notNull()); 
}

@SpecificationID(id="SESpec")
public enum SESpec implements ISpecification {

	INVOKE_SCRIPT_ACTION(Object.class, Types.ACTION_NAME, Types.ARGUMENTS),
	EVAL_SCRIPT(Object.class, Types.SCRIPT);
	
	private List<TypeDef<?>> parameterTypes;
	private TypeDef<?> returnType;
	
	SESpec(Object returnType, Object...parameterTypes ) {
		try {
			this.returnType = processType(returnType);
			this.parameterTypes = processTypes(parameterTypes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<TypeDef<?>> getParameterTypes() {
		return this.parameterTypes;
	}
	
	@Override
	public TypeDef<?> getReturnType() {
		return this.returnType;
	}
}
