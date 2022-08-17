package br.jus.tse.distribuicao_urnas.controller;

import br.jus.tse.distribuicao_urnas.model.TREDTO;
import br.jus.tse.distribuicao_urnas.service.TREService;
import br.jus.tse.distribuicao_urnas.util.WebUtils;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/tREs")
public class TREController {

    private final TREService tREService;

    public TREController(final TREService tREService) {
        this.tREService = tREService;
    }

    @GetMapping
    public String list(final Model model) {
        model.addAttribute("tREs", tREService.findAll());
        return "tRE/list";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute("tRE") final TREDTO tREDTO) {
        return "tRE/add";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("tRE") @Valid final TREDTO tREDTO,
            final BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "tRE/add";
        }
        tREService.create(tREDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("tRE.create.success"));
        return "redirect:/tREs";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable final Long id, final Model model) {
        model.addAttribute("tRE", tREService.get(id));
        return "tRE/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable final Long id,
            @ModelAttribute("tRE") @Valid final TREDTO tREDTO, final BindingResult bindingResult,
            final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "tRE/edit";
        }
        tREService.update(id, tREDTO);
        redirectAttributes.addFlashAttribute(WebUtils.MSG_SUCCESS, WebUtils.getMessage("tRE.update.success"));
        return "redirect:/tREs";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable final Long id, final RedirectAttributes redirectAttributes) {
        final String referencedWarning = tREService.getReferencedWarning(id);
        if (referencedWarning != null) {
            redirectAttributes.addFlashAttribute(WebUtils.MSG_ERROR, referencedWarning);
        } else {
            tREService.delete(id);
            redirectAttributes.addFlashAttribute(WebUtils.MSG_INFO, WebUtils.getMessage("tRE.delete.success"));
        }
        return "redirect:/tREs";
    }

}
