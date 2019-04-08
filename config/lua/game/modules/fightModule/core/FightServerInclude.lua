-- region
-- Date    : 2016-09-01
-- Author  : daiyaorong
-- Description :  
-- endregion

loadByIndex(5001, "game/modules/fightModule/core/FightControl")
loadByIndex(5002, "game/modules/fightModule/core/FightDefine")
loadByIndex(5003, "game/modules/fightModule/core/FightModel")
loadByIndex(5004, "game/modules/fightModule/core/PressureDispatcher")
loadByIndex(5005, "game/modules/fightModule/core/FightServerControl")
loadByIndex(5006, "game/modules/fightModule/core/FightControlFactory")
loadByIndex(5061, "game/modules/fightModule/core/ServerOrderHandler")

loadByIndex(5023, "game/modules/fightModule/util/MotionCalculator")
loadByIndex(5025, "game/modules/fightModule/util/PointCalculator")
loadByIndex(5026, "game/modules/fightModule/util/TrackCalculator")

loadByIndex(5051, "game/modules/fightModule/skill/SkillManager")
loadByIndex(5052, "game/modules/fightModule/skill/BaseSkill")
loadByIndex(5053, "game/modules/fightModule/skill/ActionSkill")
loadByIndex(5054, "game/modules/fightModule/skill/CollisionTool")
loadByIndex(5055, "game/modules/fightModule/skill/Bullet")
loadByIndex(5057, "game/modules/fightModule/skill/BulletSkill")
loadByIndex(5058, "game/modules/fightModule/skill/SkillEffect")
loadByIndex(5059, "game/modules/fightModule/skill/SkillPool")
loadByIndex(5060, "game/modules/fightModule/skill/PassSkillManager")
loadByIndex(5061, "game/modules/fightModule/skill/PassSkillHandler")

loadByIndex(5081, "game/modules/fightModule/skill/skillAI/JudgeCompare")
loadByIndex(5081, "game/modules/fightModule/skill/skillAI/JudgeLogic")
loadByIndex(5081, "game/modules/fightModule/skill/skillAI/SkillAI")
loadByIndex(5081, "game/modules/fightModule/skill/skillAI/SkillBehavior")
loadByIndex(5081, "game/modules/fightModule/skill/skillAI/SkillCondition")

loadByIndex(5061, "game/modules/fightModule/data/SkillVo")
loadByIndex(5061, "game/modules/fightModule/data/MonsterVo")
loadByIndex(5061, "game/modules/fightModule/data/DynamicBlockVo")
loadByIndex(5061, "game/modules/fightModule/data/SpawnAreaVo")
loadByIndex(5061, "game/modules/fightModule/data/BuffVo")
loadByIndex(5061, "game/modules/fightModule/data/SkillLevelVo")
loadByIndex(5061, "game/modules/fightModule/data/FighterVo")
loadByIndex(5061, "game/modules/fightModule/data/ServerOrderVo")

loadByIndex(5061, "game/modules/fightModule/monsterSpawn/MonsterSpawnProtocal")
loadByIndex(5061, "game/modules/fightModule/monsterSpawn/MonsterSpawnManager")
loadByIndex(5061, "game/modules/fightModule/monsterSpawn/MonsterAwakeControl")

loadByIndex(5061, "game/modules/fightModule/buff/BuffBase")
loadByIndex(5061, "game/modules/fightModule/buff/BuffManager")
loadByIndex(5061, "game/modules/fightModule/buff/CharacterBuff")
loadByIndex(5061, "game/modules/fightModule/buff/effect/BuffEffectBase")
loadByIndex(5061, "game/modules/fightModule/buff/effect/BuffEffectAttrib")
loadByIndex(5061, "game/modules/fightModule/buff/effect/BuffEffectBeatback")
loadByIndex(5061, "game/modules/fightModule/buff/effect/BuffEffectCTRL")
loadByIndex(5061, "game/modules/fightModule/buff/effect/BuffEffectCure")
loadByIndex(5061, "game/modules/fightModule/buff/effect/BuffEffectDamage")
loadByIndex(5061, "game/modules/fightModule/buff/effect/BuffEffectPhantom")
loadByIndex(5061, "game/modules/fightModule/buff/effect/BuffEffectPoisoning")
loadByIndex(5061, "game/modules/fightModule/buff/effect/BuffEffectShield")
loadByIndex(5061, "game/modules/fightModule/buff/effect/BuffEffectTaunt")
loadByIndex(5061, "game/modules/fightModule/buff/effect/BuffEffectPoisonBomb")
loadByIndex(5061, "game/modules/fightModule/buff/effect/BuffEffectSuperArmor")
loadByIndex(5061, "game/modules/fightModule/buff/effect/BuffEffectInvincible")
loadByIndex(5061, "game/modules/fightModule/buff/effect/BuffEffectHpDown")
loadByIndex(5061, "game/modules/fightModule/buff/effect/BuffEffectDamageIntensify")
loadByIndex(5061, "game/modules/fightModule/buff/effect/BuffEffectDamageAdd")
loadByIndex(5061, "game/modules/fightModule/buff/effect/BuffEffectHpPercent")
loadByIndex(5061, "game/modules/fightModule/buff/effect/BuffEffectBuffClean")
loadByIndex(5061, "game/modules/fightModule/buff/effect/BuffEffectDurationAdd")
loadByIndex(5061, "game/modules/fightModule/buff/effect/BuffEffectGetHp")
loadByIndex(5061, "game/modules/fightModule/buff/effect/BuffEffectGetDemageHp")
loadByIndex(5061, "game/modules/fightModule/buff/effect/BuffEffectNewPoisoning")
loadByIndex(5061, "game/modules/fightModule/buff/BuffEffectFactory")

loadByIndex(5061, "game/modules/fightModule/ai/base/FindPathAI")
loadByIndex(5061, "game/modules/fightModule/ai/base/FindEnemyAI")
loadByIndex(5061, "game/modules/fightModule/ai/base/ColliderCheck")
loadByIndex(5061, "game/modules/fightModule/ai/base/CharacterAI")
loadByIndex(5061, "game/modules/fightModule/ai/monsterAI/MonsterAI")
loadByIndex(5061, "game/modules/fightModule/ai/playerAI/PlayerAI")
loadByIndex(5061, "game/modules/fightModule/ai/partnerAI/PartnerAI")
loadByIndex(5061, "game/modules/fightModule/ai/partnerAI/PartnerFollowAI")
loadByIndex(5061, "game/modules/fightModule/ai/AIManager")

loadByIndex(5061, "game/modules/fightModule/rune/RuneObject")
loadByIndex(5061, "game/modules/fightModule/rune/RuneSpawnManager")
loadByIndex(5061, "game/modules/fightModule/rune/RuneSpawnPoint")

-- 模拟收取同步指令的延迟，不需要时注释掉
-- loadByIndex(5061, "game/modules/fightModule/environment/OrderDelaySimulation")