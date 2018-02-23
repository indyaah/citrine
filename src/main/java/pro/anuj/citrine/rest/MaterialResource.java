package pro.anuj.citrine.rest;

import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.anuj.citrine.domain.Material;
import pro.anuj.citrine.persistence.Criterion;
import pro.anuj.citrine.service.MaterialService;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

import static pro.anuj.citrine.Application.DEFAULT_VALUE;

@Api(tags = "Material Lookup and Creation Resource")
@RestController
public class MaterialResource {

    private final MaterialService materialService;

    public MaterialResource(MaterialService materialService) {
        this.materialService = materialService;
    }

    @ApiOperation("Create a new Material entry.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created", response = Material.class),
            @ApiResponse(code = 503, message = "Internal Server Error", response = Material.class),
            @ApiResponse(code = 400, message = "Invalid Input", response = Material.class)
    })
    @PostMapping(value = "/materials", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity search(@RequestBody @Valid Material material) {
        materialService.save(material);
        return ResponseEntity.status(201).body(material);

    }

    @ApiOperation("Look up a Material entry by various properties.")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created", response = Material.class),
            @ApiResponse(code = 503, message = "Internal Server Error", response = Material.class),
            @ApiResponse(code = 400, message = "Invalid Input", response = Material.class)
    })
    @GetMapping(value = "/materials", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Material>> search(@ApiParam(value = "Compound name to be lookedup (Free text)")
                                          @RequestParam(value = "compound", required = false) String compound,
                                          @ApiParam(value = "Compound lookup strategy", allowableValues = "like (does a fuzzy search), eq (does preice equality check)")
                                          @RequestParam(value = "compoundOp", required = false) String compoundOp,
                                          @ApiParam(value = "BandGap look up value (comma separated, for \n" +
                                                  "e.g. 1.3, 3.4 will lookup all values in between 1.3 and 3.4\n" +
                                                  "1,3, will lookup all values greater than 1.3 (comma is intentional singnigying no upper boundary)\n" +
                                                  ",4.5 will lookup all values less than 4.5 (comma is intentional singnigying no lower boundary)\n" +
                                                  "If you want to use precise equality check; use single value without comma and bandGapOp as 'eq")
                                          @RequestParam(value = "bandGap", required = false) Double bandGap[],
                                          @ApiParam(value = "Compound lookup strategy", allowableValues = "eq (precise equality), lt (less than), lte (less than equal to), gt (greater than), gte (greater than equal to)")
                                          @RequestParam(value = "bandGapOp", required = false) String bandGapOp,
                                          @ApiParam(value = "Color of the compound to be lookedup (Free text)")
                                          @RequestParam(value = "color", required = false) String color,
                                          @ApiParam(value = "Compound lookup strategy", allowableValues = "like (does a fuzzy search), eq (does preice equality check)")
                                          @RequestParam(value = "colorOp", required = false) String colorOp) {

        List<Material> search = materialService.search(compound, bandGap, color, compoundOp, bandGapOp, colorOp);
        return ResponseEntity.ok(search);
    }
}
